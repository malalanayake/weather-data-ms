package com.sysensor.app.config;

import javax.management.j2ee.statistics.JMSConnectionStats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.config.StompBrokerRelayRegistration;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import com.sysensor.app.common.config.model.BrokerContext;

/**
 * 
 * Distibution under GNU GENERAL PUBLIC LICENSE Version 2, June 1991
 * 
 * @author dmalalan
 * @created Feb 22, 2016 11:39:22 PM
 * 
 * @blog https://malalanayake.wordpress.com/
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Autowired
	ContextConfig contextConfig;
	@Autowired
  BrokerContext brokerContext;

	// TODO: Have to put the ENABLE_SIMPLE_BROKER property
	boolean enableSimpleBroker = false;
	// Set the value of the "host" header to use in STOMP CONNECT frames
	String brokerVirtualHost = "";

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.setApplicationDestinationPrefixes("/app");
		if (!enableSimpleBroker) {
			StompBrokerRelayRegistration brokerRegistration = config.enableStompBrokerRelay("/queue", "/topic")
					.setSystemLogin(brokerContext.USER_NAME).setSystemPasscode(brokerContext.PASSWORD)
					.setClientLogin(brokerContext.USER_NAME).setClientPasscode(brokerContext.PASSWORD)
					.setRelayHost(brokerContext.HOST_NAME)
					.setRelayPort(Integer.valueOf(brokerContext.STOMP_PORT));
			if (!brokerVirtualHost.equals("")) {
				brokerRegistration.setVirtualHost(brokerVirtualHost);
			}
		} else {
			config.enableSimpleBroker("/queue", "/topic");
		}
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/hello").withSockJS();
	}

}