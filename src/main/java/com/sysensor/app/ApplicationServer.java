package com.sysensor.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.sysensor.app.config.ApplicationProfileLoad;
import com.sysensor.app.config.ContextConfig;
import com.sysensor.app.config.RouteConfig;

/**
 * 
 * Distibution under GNU GENERAL PUBLIC LICENSE Version 2, June 1991
 * 
 * @author dmalalan
 * @created Apr 22, 2016 1:25:29 PM
 * 
 * @blog https://malalanayake.wordpress.com/
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@Import({ ApplicationProfileLoad.class, ContextConfig.class, RouteConfig.class })
public class ApplicationServer {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationServer.class, args);
	}

}
