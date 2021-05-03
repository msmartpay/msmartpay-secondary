package com.utility;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class PushNotification {

	/*
	 * private static final String EXCHANGE_NAME = "MSMART_SMS";
	 * 
	 * public static void main(String[] argv) throws Exception { ConnectionFactory
	 * factory = new ConnectionFactory(); factory.setHost("43.224.136.128"); try
	 * (Connection connection = factory.newConnection(); Channel channel =
	 * connection.createChannel()) { channel.exchangeDeclare(EXCHANGE_NAME,
	 * BuiltinExchangeType.FANOUT);
	 * 
	 * for (int i = 0; i < 10; i++) { String message = "Helloworld message - " + i;
	 * channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
	 * System.out.println(" [x] Sent '" + message + "'"); } } }
	 */
}
