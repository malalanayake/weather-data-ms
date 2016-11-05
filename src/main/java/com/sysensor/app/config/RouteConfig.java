package com.sysensor.app.config;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * Distribution under GNU GENERAL PUBLIC LICENSE Version 2, June 1991
 * 
 * @author dmalalan
 * @created Apr 25, 2016 8:01:58 AM
 * 
 * @blog https://malalanayake.wordpress.com/
 */
@Configuration
public class RouteConfig extends CamelConfiguration {

	@Bean
	RouteBuilder calypsoToPrincipiaRouteBuilder() {
		return new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("jms:queue:" + "Test").log("Test");
			}
		};
	}
}
