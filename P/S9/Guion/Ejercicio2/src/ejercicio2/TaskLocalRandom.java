/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author jgr97
 */
public class TaskLocalRandom implements Runnable{
    
	/**
	 * Constructor of the class. Initializes the randoom number generator
	 * for this task
	 */
	public TaskLocalRandom() {
		ThreadLocalRandom.current();
	}
	
	/**
	 * Main method of the class. Generate 10 random numbers and write them
	 * in the console
	 */
	@Override
	public void run() {
		String name=Thread.currentThread().getName();
		for (int i=0; i<10; i++){
			System.out.printf("%s: %d\n",name,ThreadLocalRandom.current().nextInt(10));
		}
	}

}
