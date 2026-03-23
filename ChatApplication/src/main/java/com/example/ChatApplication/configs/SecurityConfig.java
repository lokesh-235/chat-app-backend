package com.example.ChatApplication.configs;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.ChatApplication.entities.User;
import com.example.ChatApplication.exceptions.InvalidDetailsException;
import com.example.ChatApplication.repositories.UserRepository;
import com.example.ChatApplication.security.JwtFilter;
import com.example.ChatApplication.security.JwtService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {
	
	@Value("${frontend.url}")
	private String frontendUrl;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Autowired
	private JwtService jwtService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
	    .csrf(csrf -> csrf.disable())
	    .cors(cors -> cors.configurationSource(corsConfigurationSource()))
	    .formLogin(customizer -> customizer.disable())

	    // ✅ make stateless
	    .sessionManagement(session -> 
	        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	    )

	    .authorizeHttpRequests(auth -> auth
	        .requestMatchers(
	            "/apis/users/signup",
	            "/oauth2/**",
	            "/login/oauth2/**",
	            "/apis/users/authenticate"
	        ).permitAll()
	        .anyRequest().authenticated()
	    )

	    // ❌ REMOVE httpBasic

	    .oauth2Login(oauth -> oauth.successHandler((request, response, authentication) -> {

	        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();

	        String email = oauthUser.getAttribute("email");
	        String name = oauthUser.getAttribute("name");

	        // ✅ auto-create user
	        User user = userRepository.findByEmail(email)
	            .orElseGet(() -> {
	                User newUser = new User();
	                newUser.setEmail(email);
	                newUser.setUsername(name);
	                newUser.setPassword("password");
	                return userRepository.save(newUser);
	            });

	        // ✅ real JWT
	        String token = jwtService.generateToken(user.getEmail());
	        
	        System.out.println("Token : "+token);

	        ResponseCookie cookie = ResponseCookie.from("jwt", token)
	                .httpOnly(true)
	                .secure(false) // true if HTTPS
	                .path("/")
	                .maxAge(24 * 60 * 60)
	                .sameSite("Lax") // or "None" if cross-origin
	                .build();

	        response.setHeader("Set-Cookie", cookie.toString());
	        
//	        response.addCookie(cookie);
	        response.sendRedirect(frontendUrl + "/rooms");
	    }))

	    // ✅ enable JWT filter
	    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
	    .exceptionHandling(ex -> ex
	    	    .authenticationEntryPoint((request, response, authException) -> {
	    	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	    	    })
	    	)
	    ;

	    return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration config = new CorsConfiguration();

	    config.setAllowedOrigins(List.of(frontendUrl));
	    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
	    config.setAllowCredentials(true); // 🔥 REQUIRED for cookies

	    config.setAllowedHeaders(List.of("*"));

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", config);

	    return source;
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsService);
		
		authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
		
		return authenticationProvider;
		
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
}
