package com.sysensor.app.common.config.model;

/**
 * 
 * Distibution under GNU GENERAL PUBLIC LICENSE Version 2, June 1991
 * 
 * @author dmalalan
 * @created Apr 22, 2016 3:22:54 PM
 * 
 * @blog https://malalanayake.wordpress.com/
 */
public class BrokerContext {
	public final boolean START_EMBEDDED_BROKER;
	public final String HOST_NAME;
	public final String TCP_PORT;
	public final String STOMP_PORT;
	public final String USER_NAME;
	public final String PASSWORD;

	private BrokerContext(BrokerContextBuilder builder) {
		this.START_EMBEDDED_BROKER = builder.B_START_EMBEDDED_BROKER;
		this.HOST_NAME = builder.B_HOST_NAME;
		this.TCP_PORT = builder.B_TCP_PORT;
		this.STOMP_PORT = builder.B_STOMP_PORT;
		this.USER_NAME = builder.B_USER_NAME;
		this.PASSWORD = builder.B_PASSWORD;
	}

	public static class BrokerContextBuilder {
		private boolean B_START_EMBEDDED_BROKER;
		private String B_HOST_NAME;
		private String B_TCP_PORT;
		private String B_STOMP_PORT;
		private String B_USER_NAME;
		private String B_PASSWORD;

		private static BrokerContextBuilder brokerContextBuilder = null;

		public static BrokerContextBuilder getInstance() {
			if (brokerContextBuilder != null) {
				return brokerContextBuilder;
			} else {
				return new BrokerContextBuilder();
			}
		}

		private BrokerContextBuilder() {

		}

		public BrokerContextBuilder startEmbeddedBroker(boolean embeddedBroker) {
			this.B_START_EMBEDDED_BROKER = embeddedBroker;
			return this;
		}

		public BrokerContextBuilder hostName(String hostName) {
			this.B_HOST_NAME = hostName;
			return this;
		}

		public BrokerContextBuilder tcpPort(String tcpPort) {
			this.B_TCP_PORT = tcpPort;
			return this;
		}

		public BrokerContextBuilder stompPort(String stompPort) {
			this.B_STOMP_PORT = stompPort;
			return this;
		}

		public BrokerContextBuilder userName(String userName) {
			this.B_USER_NAME = userName;
			return this;
		}

		public BrokerContextBuilder passWord(String passWord) {
			this.B_PASSWORD = passWord;
			return this;
		}

		public BrokerContext build() {
			return new BrokerContext(this);
		}
	}
}
