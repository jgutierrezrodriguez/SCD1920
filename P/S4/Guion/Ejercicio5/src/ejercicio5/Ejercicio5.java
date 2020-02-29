/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author jgr97
 */
public class Ejercicio5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ExecutionException {
        // Create an executor
		ExecutorService executor=(ExecutorService)Executors.newCachedThreadPool();

		// Create three tasks and stores them in a List
		List<Task> taskList=new ArrayList<>();
		for (int i=0; i<3; i++){
			Task task=new Task("Task-"+i);
			taskList.add(task);
		}

		// Call the invokeAll() method
		List<Future<Result>>resultList=null;
		try {
			resultList=executor.invokeAll(taskList);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Finish the executor
		executor.shutdown();
		
		// Writes the results to the console
		System.out.printf("Core: Printing the results\n");
		for (int i=0; i<resultList.size(); i++){
			Future<Result> future=resultList.get(i);
			try {
				Result result=future.get();
				System.out.printf("%s: %s\n",result.getName(),result.getValue());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			} 
		}

    }
    
}
