/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 *
 * @author jgr97
 */
public class Task implements Callable<String>{
   /**
	 * Name of the task
	 */
	private String name;
	
	/**
	 * Constructor of the class
	 * @param name Name of the task
	 */
	public Task(String name) {
		this.name=name;
	}
	
	/**
	 * Main method of the task. Writes a message to the console with
	 * the actual date and returns the 'Hello world' string
	 */
	@Override
	public String call() throws Exception {
		System.out.printf("%s: Starting at : %s\n",name,new Date());
		return "Hello, world";
	}
 
}
