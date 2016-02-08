package io.demo;
/**
 * Copyright (c) 2015 Donnacha Forde. All rights reserved.
 *
 */

import java.util.concurrent.CountDownLatch;

/**
 * Created by dforde on 28/08/2015.
 */
public class Receiver
{

    private CountDownLatch latch = new CountDownLatch(1);

	public void receiveMessage(String message)
	{
		System.out.println("Received <" + message + ">");
		latch.countDown();
    	}

   	public CountDownLatch getLatch()
        {
    		return latch;
    	}

}
