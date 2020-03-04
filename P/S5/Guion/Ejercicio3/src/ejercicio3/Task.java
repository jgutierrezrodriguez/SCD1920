/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3;

import java.util.concurrent.Callable;

/**
 *
 * @author jgr97
 */
public class Task implements Callable<String>{
    
	/**
	 * Main method of the task. It has an infinite loop that writes a message to
	 * the console every 100 milliseconds
	 */
	@Override
	public String call() throws Exception {
		while (true){
			System.out.printf("Task: Test\n");
			Thread.sleep(100);
		}
	}

}
