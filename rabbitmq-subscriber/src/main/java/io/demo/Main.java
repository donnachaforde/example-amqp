package io.demo;
/**
 * Copyright (c) 2015 Donnacha Forde. All rights reserved.
 *
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main - <desc>
 *
 * @author Donnacha Forde
 * @version Version 0.1 Sep 2015
 * @since X0.0.1
 */
@SpringBootApplication
public class Main
{
    //-------------------------------------------------------------------------
    // main statement

    public static void main(String[] args) throws InterruptedException
    {
        System.out.println("**************************************************************************************");
        System.out.println("Starting Message Subscriber....");
        SpringApplication.run(Application.class, args);
        System.out.println("Finished Message Subscriber.");
        System.out.println("**************************************************************************************");
    }


}
