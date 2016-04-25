package com.sysensor.app.config;

import javax.jms.ConnectionFactory;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jms.connection.JmsTransactionManager;

import com.sysensor.app.common.config.DBConfig;
import com.sysensor.app.common.config.JMSBrokerConfig;
import com.sysensor.app.common.config.impl.ActiveMqConfigImpl;
import com.sysensor.app.common.config.impl.DBConfigImpl;
import com.sysensor.app.common.config.model.BrokerContext;
import com.sysensor.app.common.config.model.DBContext;

/**
 * 
 * Distibution under GNU GENERAL PUBLIC LICENSE Version 2, June 1991
 * 
 * @author dmalalan
 * @created Apr 22, 2016 1:26:04 PM
 * 
 * @blog https://malalanayake.wordpress.com/
 */
@Configuration
public class ContextConfig {
	private Logger log = Logger.getLogger(getClass().getName());

	public final String APP_ENV;

	private JMSBrokerConfig jmsBrokerConfig;
	private DBConfig dbConfig;

	public ContextConfig() {
		APP_ENV = System.getProperty("APP_ENV");

		jmsBrokerConfig = ActiveMqConfigImpl.getInstance();
		dbConfig = DBConfigImpl.getInstance();
	}

	@Bean
	public PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
		log.info("[START:Load Application Properties]");

		String propertiesFilename = "/application-" + APP_ENV + ".properties";

		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		configurer.setLocation(new ClassPathResource(propertiesFilename));
		log.info("[ACTIVATE:Profile " + APP_ENV + "]");
		log.info("[END:Load Application Properties]");

		return configurer;
	}

	// *******Expose ActiveMQ Configuration******
	@Bean
	public ConnectionFactory connectionFactory() {
		return this.jmsBrokerConfig.connectionFactory();
	}

	@Bean
	public JmsTransactionManager jmsTransactionManager() {
		return this.jmsBrokerConfig.JmsTransactionManager();
	}

	@Bean
	public BrokerContext brokerContext() {
		return jmsBrokerConfig.getBrokerContext();
	}

	// *******Expose DB Configuration*******
	@Bean
	public DataSource dataSource() {
		return dbConfig.dataSource();
	}

	@Bean
	public DBContext dbContext() {
		return dbConfig.getDBContext();
	}

}
