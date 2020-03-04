/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

import java.util.Date;

/**
 *
 * @author jgr97
 */
public class Task implements Runnable{
    /**
	 * Name of the task
	 */
	private String name;
	
	/**
	 * Constructor of the class
	 * @param name the name of the class
	 */
	public Task(String name) {
		this.name=name;
	}

	/**
	 * Main method of the example. Writes a message to the console with the actual
	 * date
	 */
	@Override
	public void run() {
		System.out.printf("%s: Executed at: %s\n",name,new Date());
	}

}
