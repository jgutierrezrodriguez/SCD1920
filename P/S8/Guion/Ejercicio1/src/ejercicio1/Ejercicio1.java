/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 *
 * @author jgr97
 */
public class Ejercicio1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // Create a ConcurrentLinkedDeque to work with it in the example
		ConcurrentLinkedDeque<String> list=new ConcurrentLinkedDeque<>();
		// Create an Array of 100 threads
		Thread threads[]=new Thread[100];

		// Create 100 AddTask objects and execute them as threads
		for (int i=0; i<threads.length; i++){
			AddTask task=new AddTask(list);
			threads[i]=new Thread(task);
			threads[i].start();
		}
		System.out.printf("Main: %d AddTask threads have been launched\n",threads.length);
		
		// Wait for the finalization of the threads
		for (int i=0; i<threads.length; i++) {
				threads[i].join();
		}
		
		// Write to the console the size of the list
		System.out.printf("Main: Size of the List: %d\n",list.size());
		
		// Create 100 PollTask objects and execute them as threads
		for (int i=0; i<threads.length; i++){
			PollTask task=new PollTask(list);
			threads[i]=new Thread(task);
			threads[i].start();
		}
		System.out.printf("Main: %d PollTask threads have been launched\n",threads.length);
		
		// Wait for the finalization of the threads
		for (int i=0; i<threads.length; i++) {
			threads[i].join();
		}
		
		// Write to the console the size of the list
		System.out.printf("Main: Size of the List: %d\n",list.size());

    }
    
}
