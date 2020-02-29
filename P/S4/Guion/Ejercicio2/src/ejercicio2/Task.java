/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jgr97
 */
public class Task implements Runnable{
    private Date initDate;
	
	/**
	 * The name of the task
	 */
	private String name;
	
	/**
	 * Constructor of the class. Initializes the name of the task
	 * @param name Name assigned to the task
	 */
	public Task(String name){
		initDate=new Date();
		this.name=name;
	}
	
	/**
	 * This method implements the execution of the task. Waits a random period of time and finish
	 */	
	@Override
	public void run() {
		System.out.printf("%s: Task %s: Created on: %s\n",Thread.currentThread().getName(),name,initDate);
		System.out.printf("%s: Task %s: Started on: %s\n",Thread.currentThread().getName(),name,new Date());
		
		try {
			Long duration=(long)(Math.random()*10);
			System.out.printf("%s: Task %s: Doing a task during %d seconds\n",Thread.currentThread().getName(),name,duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.printf("%s: Task %s: Finished on: %s\n",Thread.currentThread().getName(),new Date(),name);
	}

}
