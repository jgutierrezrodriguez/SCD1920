/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio5;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jgr97
 */
public class Task implements Callable<Result> {

	/**
	 * The name of the Task
	 */
	private String name;
	
	/**
	 * Constructor of the class
	 * @param name Initializes the name of the task
	 */
	public Task(String name) {
		this.name=name;
	}
	
	/**
	 * Main method of the task. Waits during a random period of time and then
	 * calculates the sum of five random numbers
	 */
	@Override
	public Result call() throws Exception {
		// Writes a message to the console
		System.out.printf("%s: Staring\n",this.name);
		
		// Waits during a random period of time
		try {
			Long duration=(long)(Math.random()*10);
			System.out.printf("%s: Waiting %d seconds for results.\n",this.name,duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		
		// Calculates the sum of five random numbers
		int value=0;
		for (int i=0; i<5; i++){
			value+=(int)(Math.random()*100);

		}
		
		// Creates the object with the results
		Result result=new Result();
		result.setName(this.name);
		result.setValue(value);
		System.out.printf("%s: Ends\n",this.name);

		// Returns the result object
		return result;
	}

}

    

