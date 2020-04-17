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
public class Decrementar implements Runnable{
    /**
	 * The array to decrement the elements
	 */
	private AtomicIntegerArray vector; 
	
	/**
	 * Constructor of the class
	 * @param vector The array to decrement is elements
	 */
	public Decrementar(AtomicIntegerArray vector) {
		this.vector=vector;
	}
	
	/**
	 * Main method of the class. It decrements all the elements of the 
	 * array
	 */
	@Override
	public void run() {
		for (int i=0; i<vector.length(); i++) {
			vector.getAndDecrement(i);
		}	
	}

}
