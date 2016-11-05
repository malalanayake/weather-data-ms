package com.sysensor.app.config;

import com.sysensor.app.common.config.DBConfig;
import com.sysensor.app.common.config.JMSBrokerConfig;
import com.sysensor.app.common.config.model.BrokerContext;
import com.sysensor.app.common.config.model.DBContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.JmsTransactionManager;

import javax.jms.ConnectionFactory;
import javax.sql.DataSource;

/**
 * 
 * Distribution under GNU GENERAL PUBLIC LICENSE Version 2, June 1991
 *
 * @author dmalalan
 * @created Apr 22, 2016 1:26:04 PM
 * 
 * @blog https://malalanayake.wordpress.com/
 */
@Configuration
public class ContextConfig {
	private Logger log = Logger.getLogger(getClass().getName());

	@Autowired
	private JMSBrokerConfig jmsBrokerConfig;
	@Autowired
	private DBConfig dbConfig;

	@Value("${LOG_TOPIC_NAME}")
	public String LOG_TOPIC_NAME;

	@Value("${GOOGLE_API_KEY}")
	public String GOOGLE_API_KEY;


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
