package com.sysensor.app.common.messaging.impl;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysensor.app.common.config.model.BrokerContext;
import com.sysensor.app.common.messaging.MessageService;
import com.sysensor.app.common.messaging.model.SYSContainer;

/**
 * 
 * Distibution under GNU GENERAL PUBLIC LICENSE Version 2, June 1991
 * 
 * @author dmalalan
 * @created Apr 25, 2016 8:00:14 AM
 * 
 * @blog https://malalanayake.wordpress.com/
 */
@Service
public class SYSContainerMessageServiceImpl implements MessageService<SYSContainer> {

	private Logger log = Logger.getLogger(getClass().getName());

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	BrokerContext brokerContext;
	@Autowired
	ObjectMapper mapper;

	@Override
	public void pushToQueue(String queueName, SYSContainer type) {
		StringBuilder json = new StringBuilder();
		Destination activeMQQueue = new ActiveMQQueue(queueName);
		if (brokerContext.ENABLE_JMS_PLAINTEXT) {
			try {
				json.append(mapper.writeValueAsString(type));
			} catch (Exception e) {
				e.printStackTrace();
			}

			log.info("[Message: " + json.toString() + "]");
			jmsTemplate.convertAndSend(activeMQQueue, json.toString());
		} else {
			jmsTemplate.convertAndSend(activeMQQueue, type);
		}
	}

	@Override
	public void pushToTopic(String topicName, SYSContainer type) {
		StringBuilder json = new StringBuilder();
		Destination activeMQTopic = new ActiveMQTopic(topicName);
		if (brokerContext.ENABLE_JMS_PLAINTEXT) {
			try {
				json.append(mapper.writeValueAsString(type));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			log.info("[Message: " + json.toString() + "]");
			jmsTemplate.convertAndSend(activeMQTopic, json.toString());
		} else {
			jmsTemplate.convertAndSend(activeMQTopic, type);
		}
	}

}
