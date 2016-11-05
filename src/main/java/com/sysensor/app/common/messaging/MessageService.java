package com.sysensor.app.common.messaging;

/**
 * 
 * Distribution under GNU GENERAL PUBLIC LICENSE Version 2, June 1991
 * 
 * @author dmalalan
 * @created Apr 25, 2016 8:00:02 AM
 * 
 * @blog https://malalanayake.wordpress.com/
 */
public interface MessageService<T> {
	public void pushToQueue(String queueName, T type);

	public void pushToTopic(String topicName, T type);
}
