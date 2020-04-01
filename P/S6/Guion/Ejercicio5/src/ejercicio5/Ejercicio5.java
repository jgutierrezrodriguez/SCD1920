/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio5;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jgr97
 */
public class Ejercicio5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                ArrayGenerator generator=new ArrayGenerator();
		int array[]=generator.generateArray(1000);
		
		// Create a TaskManager object
		TaskManager manager=new TaskManager();
		
		// Create a ForkJoinPool with the default constructor
		ForkJoinPool pool=new ForkJoinPool();
		
		// Create a Task to process the array
		SearchNumberTask task=new SearchNumberTask(array,0,1000,5,manager);
		
		// Execute the task
		pool.execute(task);

		// Shutdown the pool
		pool.shutdown();
		
		
		// Wait for the finalization of the task
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Write a message to indicate the end of the program
		System.out.printf("Main: The program has finished\n");

    }
    
}
