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
public class Task implements Runnable {

	/**
	 * Id of the task
	 */
	private int id;

	/**
	 * Priority queue to store the events
	 */
	private PriorityBlockingQueue<Event> queue;
	
	/**
	 * Constructor of the class. It initializes its attributes
	 * @param id Id of the task 
	 * @param queue Priority queue to store the events
	 */
	public Task(int id, PriorityBlockingQueue<Event> queue) {
		this.id=id;
		this.queue=queue;
	}
	
	/**
	 * Main method of the task. It generates 1000 events and store
	 * them in the queue
	 */
	@Override
	public void run() {
		for (int i=0; i<1000; i++){
			Event event=new Event(id,i);
			queue.add(event);
		}
	}
}

