package com.sysensor.app.service.messaging.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysensor.app.common.messaging.MessageService;
import com.sysensor.app.common.messaging.model.SYSContainer;
import com.sysensor.app.config.ContextConfig;
import com.sysensor.app.service.messaging.LogMessageService;

/**
 * 
 * Distibution under GNU GENERAL PUBLIC LICENSE Version 2, June 1991
 * 
 * @author dmalalan
 * @created Apr 25, 2016 10:22:39 AM
 * 
 * @blog https://malalanayake.wordpress.com/
 */
@Service
public class LogMessageServiceImpl implements LogMessageService {
	private Logger log = Logger.getLogger(getClass().getName());

	@Autowired
	ContextConfig contextConfig;

	@Autowired
	MessageService<SYSContainer> messageService;

	public void publishLogs(String message) {
		log.info("[LOG:Start pubishing log message]");

		SYSContainer sysContainer = SYSContainer.getInstance();
		sysContainer.setMessage(message);
		messageService.pushToTopic(contextConfig.LOG_TOPIC_NAME, sysContainer);

		log.info("[LOG:End pubishing log message]");
	}

}
