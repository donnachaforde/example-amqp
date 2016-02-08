package io.demo;
/**
 * Copyright (c) 2015 Donnacha Forde. All rights reserved.
 *
 */

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * Application - <desc>
 *
 * @author Donnacha Forde
 * @version Version 0.1 Sep 2015
 * @since X0.0.1
 */
@Service
@ComponentScan
public class Application implements CommandLineRunner
{
    //-------------------------------------------------------------------------
    // member vars

    final static String queueName = "spring-boot";

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AnnotationConfigApplicationContext context;


    //-------------------------------------------------------------------------
    // construction

    /**
     * Default constructor - required for serialization purposes.
     * <p>
     * Note: This ctor is not intended to be used directly.
     */
    public Application()
    {
        System.out.println("Starting Listener Application....");
    }



    //-------------------------------------------------------------------------
    // beans

    @Bean
    Queue queue()
    {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange()
    {
        return new TopicExchange("demo-messaging-exchange");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange)
    {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }


    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter)
    {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);

        return container;
    }

    @Bean
    Receiver receiver()
    {
        return new Receiver();
    }


    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver)
    {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }


    //-------------------------------------------------------------------------
    // springboot run

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception
    {
        System.out.println("Waiting five seconds...");
        Thread.sleep(5000);

    }

}
