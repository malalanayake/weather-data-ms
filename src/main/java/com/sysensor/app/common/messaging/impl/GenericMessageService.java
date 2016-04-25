package com.sysensor.app.common.messaging.impl;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.sysensor.app.common.messaging.MessageService;
import com.sysensor.app.common.messaging.model.SysensorContainer;;

@Service
public class GenericMessageService implements MessageService<SysensorContainer> {

	private static final String SIMPLE_QUEUE = "simple.queue";

	private final JmsTemplate jmsQueueTemplate;
	private final JmsTemplate jmsTopicTemplate;

	@Autowired
	public GenericMessageService(JmsTemplate jmsQueueTemplate, JmsTemplate jmsTopicTemplate) {
		this.jmsQueueTemplate = jmsQueueTemplate;
		this.jmsTopicTemplate = jmsTopicTemplate;
		this.jmsQueueTemplate.setPubSubDomain(false);
		this.jmsTopicTemplate.setPubSubDomain(true);
	}

	@Override
	public void pushToQueue(String queueName, SysensorContainer type) {
		jmsQueueTemplate.convertAndSend(queueName, type);
	}

	@Override
	public void pushToTopic(String topicName, SysensorContainer type) {
		jmsTopicTemplate.convertAndSend(topicName, type);
		
	}

}
