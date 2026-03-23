package com.example.ChatApplication.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig {
	
	@Value("${frontend.url}")
	String frontendUrl;
	@Bean
	public WebSocketMessageBrokerConfigurer messageBrokerConfigurer() {
		
		WebSocketMessageBrokerConfigurer configurer = new WebSocketMessageBrokerConfigurer() {
			
			@Override
			public void configureMessageBroker(MessageBrokerRegistry registry) {
				// TODO Auto-generated method stub
				registry.enableSimpleBroker("/topic");
				registry.setApplicationDestinationPrefixes("/app");
			}
			
			@Override
			public void registerStompEndpoints(StompEndpointRegistry registry) {
				// TODO Auto-generated method stub
				registry.addEndpoint("/chat").setAllowedOrigins(frontendUrl).withSockJS();
			}
		};
		
		return configurer;
	}
	
}
