package io.demo;
/**
 * Copyright (c) 2015 Donnacha Forde. All rights reserved.
 *
 */

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
        System.out.println("Starting Publisher Application....");
    }

    /**
     * Factory method - create an instance of an alert.
     *
     * @return - new TemperatureSensor instance.
     */
    public static Application createInstance() 
    {
        return new Application();
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
        return new TopicExchange("spring-boot-exchange");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange)
    {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
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
        
        System.out.println("Starting message loop...");
        for ( ; ;)
        {
            System.out.println("Sending 10 messages...");
            for (int i = 0; i < 10; i++)
            {
                rabbitTemplate.convertAndSend(queueName, "Hello from my RabbitMQ! - msg " + i);
            }

            System.out.println("Completed sending 10 messages.");
            Thread.sleep(2000);
        }

        //context.close();
    }

}
