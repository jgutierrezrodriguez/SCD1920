/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jgr97
 */
public class Ejercicio1 {

    /**
 * Main class of the example. Send 5 tasks to an scheduled executor
 *   Task 0: Delay of 1 second
 *   Task 1: Delay of 2 seconds
 *   Task 2: Delay of 3 seconds
 *   Task 3: Delay of 4 seconds
 *   Task 4: Delay of 5 seconds 
 *
     * @param args
 */

    public static void main(String[] args) {
        ScheduledExecutorService executor=(ScheduledExecutorService)Executors.newScheduledThreadPool(1);
		
		System.out.printf("Main: Starting at: %s\n",new Date());
		
		// Send the tasks to the executor with the specified delay
		for (int i=0; i<5; i++) {
			Task task=new Task("Task "+i);
			executor.schedule(task,i+1 , TimeUnit.SECONDS);
		}
		
		// Finish the executor
		executor.shutdown();
		
		// Waits for the finalization of the executor
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Writes the finalization message
		System.out.printf("Core: Ends at: %s\n",new Date());

    }
    
}