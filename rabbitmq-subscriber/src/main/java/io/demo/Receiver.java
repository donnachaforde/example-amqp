package io.demo;
/**
 * Copyright (c) 2015 Donnacha Forde. All rights reserved.
 *
 */

/**
 * Receiver - Callback receiver class.
 *
 * @author Donnacha Forde
 * @version Version 0.1 Sep 2015
 * @since X0.0.1
 */
public class Receiver
{

	public void receiveMessage(String message)
	{
		System.out.println("Received <" + message + ">");
    }

}
