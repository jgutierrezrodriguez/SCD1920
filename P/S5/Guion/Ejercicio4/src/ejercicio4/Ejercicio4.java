/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio4;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jgr97
 */
public class Ejercicio4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ExecutionException {
		// Create an executor
		ExecutorService executor=(ExecutorService)Executors.newCachedThreadPool();
		
		//Create five tasks
		ResultTask resultTasks[]=new ResultTask[5];
		for (int i=0; i<5; i++) {
			ExecutableTask executableTask=new ExecutableTask("Task "+i);
			resultTasks[i]=new ResultTask(executableTask);
			executor.submit(resultTasks[i]);
		}
		
		// Sleep the thread five seconds
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		// Cancel all the tasks. In the tasks that have finished before this moment, this
		// cancellation has no effects
		for (int i=0; i<resultTasks.length; i++) {
			resultTasks[i].cancel(true);
		}
		
		// Write the results of those tasks that haven't been cancelled
		for (int i=0; i<resultTasks.length; i++) {
			try {
				if (!resultTasks[i].isCancelled()){
					System.out.printf("%s\n",resultTasks[i].get());
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			} 
		}
		// Finish the executor.
		executor.shutdown();

	}

}


