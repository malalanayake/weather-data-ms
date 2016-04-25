package com.sysensor.app.config;

import org.springframework.context.annotation.Configuration;

import com.sysensor.app.common.config.AbstractApplicationProfileLoad;

/**
 * 
 * Distibution under GNU GENERAL PUBLIC LICENSE Version 2, June 1991
 * 
 * @author dmalalan
 * @created Apr 25, 2016 8:20:28 AM
 * 
 * @blog https://malalanayake.wordpress.com/
 */
@Configuration
public class ApplicationProfileLoad extends AbstractApplicationProfileLoad {

	public static String APP_NAME = "weather-data-ms";

	@Override
	public String getApplicationName() {
		return APP_NAME;
	}

}
