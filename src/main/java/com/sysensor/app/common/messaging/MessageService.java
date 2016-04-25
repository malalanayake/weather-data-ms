package com.sysensor.app.common.messaging;

public interface MessageService<T> {
	public void pushToQueue(String queueName, T type);

	public void pushToTopic(String topicName, T type);
}
