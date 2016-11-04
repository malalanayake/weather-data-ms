package com.sysensor.app.common.config.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jms.ConnectionFactory;

import org.apache.activemq.broker.BrokerPlugin;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.apache.activemq.security.AuthenticationUser;
import org.apache.activemq.security.SimpleAuthenticationPlugin;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;
import org.springframework.jms.connection.JmsTransactionManager;

import com.sysensor.app.common.config.JMSBrokerConfig;
import com.sysensor.app.common.config.model.BrokerContext;
import com.sysensor.app.common.config.model.BrokerContext.BrokerContextBuilder;

/**
 * Distribution under GNU GENERAL PUBLIC LICENSE Version 2, June 1991
 *
 * @author dmalalan
 * @created Apr 22, 2016 1:26:04 PM
 * @blog https://malalanayake.wordpress.com/
 */
public class ActiveMqConfigImpl implements JMSBrokerConfig {
    private Logger log = Logger.getLogger(getClass().getName());

    private static final String ROLES = "admins,publishers,consumers";
    private static final String STOMP = "stomp";
    private static final String TCP = "tcp";

    private final BrokerContext brokerContext;
    private final boolean START_EMBEDDED_BROKER;
    private final boolean ENABLE_JMS_PLAINTEXT;
    private final String ACTIVEMQ_HOST_NAME;
    private final String ACTIVEMQ_TCP_PORT;
    private final String ACTIVEMQ_STOMP_PORT;
    private final String ACTIVEMQ_USER_NAME;
    private final String ACTIVEMQ_PASSWORD;

    private static JMSBrokerConfig jmsBrokerConfig = null;

    public static JMSBrokerConfig getInstance() {
        if (jmsBrokerConfig != null) {
            return jmsBrokerConfig;
        } else {
            return new ActiveMqConfigImpl();
        }
    }

    private ActiveMqConfigImpl() {

        START_EMBEDDED_BROKER = Boolean.valueOf(System.getProperty("START_EMBEDDED_BROKER"));
        ENABLE_JMS_PLAINTEXT = Boolean.valueOf(System.getProperty("ENABLE_JMS_PLAINTEXT"));
        ACTIVEMQ_HOST_NAME = System.getProperty("ACTIVEMQ_HOST_NAME");
        ACTIVEMQ_TCP_PORT = System.getProperty("ACTIVEMQ_TCP_PORT");
        ACTIVEMQ_STOMP_PORT = System.getProperty("ACTIVEMQ_STOMP_PORT");
        ACTIVEMQ_USER_NAME = System.getProperty("ACTIVEMQ_USER_NAME");
        ACTIVEMQ_PASSWORD = System.getProperty("ACTIVEMQ_PASSWORD");

        startActiveMqBroker();
        brokerContext = BrokerContextBuilder.getInstance().startEmbeddedBroker(START_EMBEDDED_BROKER)
                .hostName(ACTIVEMQ_HOST_NAME).tcpPort(ACTIVEMQ_TCP_PORT).stompPort(ACTIVEMQ_STOMP_PORT)
                .userName(ACTIVEMQ_USER_NAME).passWord(ACTIVEMQ_PASSWORD).enableJMSPlainText(ENABLE_JMS_PLAINTEXT).build();
    }

    public ConnectionFactory amqConnectionFactory() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL("failover:(" + TCP + "://" + ACTIVEMQ_HOST_NAME + ":" + ACTIVEMQ_TCP_PORT
                + "?closeAsync=false)?randomize=false");
        factory.setUserName(ACTIVEMQ_USER_NAME);
        factory.setPassword(ACTIVEMQ_PASSWORD);
        return factory;
    }

    private void startActiveMqBroker() {
        BrokerService broker = new BrokerService();
        if (START_EMBEDDED_BROKER) {
            log.info("[START:ActiveMQ Broker]");
            try {
                SimpleAuthenticationPlugin authentication = new SimpleAuthenticationPlugin();

                List<AuthenticationUser> users = new ArrayList<AuthenticationUser>();
                users.add(new AuthenticationUser(ACTIVEMQ_USER_NAME, ACTIVEMQ_PASSWORD, ROLES));
                authentication.setUsers(users);
                broker.setPlugins(new BrokerPlugin[]{authentication});

                broker.addConnector(TCP + "://" + ACTIVEMQ_HOST_NAME + ":" + ACTIVEMQ_TCP_PORT);
                broker.addConnector(STOMP + "://" + ACTIVEMQ_HOST_NAME + ":" + ACTIVEMQ_STOMP_PORT);
                broker.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ConnectionFactory connectionFactory() {
        PooledConnectionFactory factory = new PooledConnectionFactory();
        factory.setMaxConnections(3);
        factory.setConnectionFactory(amqConnectionFactory());
        return factory;
    }


    public JmsTransactionManager JmsTransactionManager() {
        JmsTransactionManager manager = new JmsTransactionManager();
        manager.setConnectionFactory(amqConnectionFactory());
        return manager;
    }


    public BrokerContext getBrokerContext() {
        return brokerContext;
    }

}
