/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio4;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 *
 * @author jgr97
 */
public class Incrementer implements Runnable{
    /**
	 * Array that store the elements to increment
	 */
	private AtomicIntegerArray vector; 
	
	/**
	 * Constructor of the class
	 * @param vector Array to store the elements to increment
	 */
	public Incrementer(AtomicIntegerArray vector) {
		this.vector=vector;
	}
	
	/**
	 * Main method of the task. Increment all the elements of the
	 * array
	 */
	@Override
	public void run() {
		
		for (int i=0; i<vector.length(); i++){
			vector.getAndIncrement(i);
		}
		
	}

}
