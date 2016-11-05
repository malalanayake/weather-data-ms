package com.sysensor.app.common.config;

import javax.jms.ConnectionFactory;

import org.springframework.jms.connection.JmsTransactionManager;

import com.sysensor.app.common.config.model.BrokerContext;

/**
 * 
 * Distribution under GNU GENERAL PUBLIC LICENSE Version 2, June 1991
 * 
 * @author dmalalan
 * @created Apr 22, 2016 3:22:32 PM
 * 
 * @blog https://malalanayake.wordpress.com/
 */
public interface JMSBrokerConfig {

	public BrokerContext getBrokerContext();

	public ConnectionFactory connectionFactory();

	public JmsTransactionManager JmsTransactionManager();

}
