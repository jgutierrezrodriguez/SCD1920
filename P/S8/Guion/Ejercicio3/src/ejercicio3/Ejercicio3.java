/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3;

import java.util.concurrent.PriorityBlockingQueue;

/**
 *
 * @author jgr97
 */
public class Ejercicio3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
		 * Priority queue to store the events
		 */
		PriorityBlockingQueue<Event> queue=new PriorityBlockingQueue<>();
		
		/*
		 * An array to store the five Thread objects
		 */
		Thread taskThreads[]=new Thread[5];
		
		/*
		 * Create the five threads to execute five tasks
		 */
		for (int i=0; i<taskThreads.length; i++){
			Task task=new Task(i,queue);
			taskThreads[i]=new Thread(task);
		}
		
		/*
		 * Start the five threads
		 */
		for (int i=0; i<taskThreads.length; i++) {
			taskThreads[i].start();
		}
		
		/*
		 * Wait for the finalization of the five threads
		 */
		for (int i=0; i<taskThreads.length; i++) {
			try {
				taskThreads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		/*
		 * Write the events in the console 
		 */
		System.out.printf("Main: Queue Size: %d\n",queue.size());
		for (int i=0; i<taskThreads.length*1000; i++){
			Event event=queue.poll();
			System.out.printf("Thread %s: Priority %d\n",event.getThread(),event.getPriority());
		}
		System.out.printf("Main: Queue Size: %d\n",queue.size());
		System.out.printf("Main: End of the program\n");

    }
    
}
