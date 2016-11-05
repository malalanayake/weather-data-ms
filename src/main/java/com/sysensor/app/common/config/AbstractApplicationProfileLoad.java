package com.sysensor.app.common.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * 
 * Distribution under GNU GENERAL PUBLIC LICENSE Version 2, June 1991
 * 
 * @author dmalalan
 * @created Apr 25, 2016 8:16:49 AM
 * 
 * @blog https://malalanayake.wordpress.com/
 */
public abstract class AbstractApplicationProfileLoad {

	private Logger log = Logger.getLogger(getClass().getName());
	private final String APP_ENV = "APP_ENV";

	/**
	 * Implement this method in application
	 * 
	 * @return
	 */
	public abstract String getApplicationName();

	@Bean
	public PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
		log.info("[START:Load Application Properties]");

		String activeProfile = System.getenv(APP_ENV);
		String appName = getApplicationName();
		String propertiesFilename = "/" + appName + "-" + activeProfile + ".properties";

		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		configurer.setLocation(new ClassPathResource(propertiesFilename));
		log.info("[ACTIVATE:Profile " + propertiesFilename + "]");
		log.info("[END:Load Application Properties]");
		return configurer;
	}
}