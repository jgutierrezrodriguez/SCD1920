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
public class Ejercicio4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int THREADS=100;
		/**
		 * Atomic array whose elements will be incremented and decremented
		 */
		AtomicIntegerArray vector=new AtomicIntegerArray(1000);
		/*
		 * An incrementer task
		 */
		Incrementar incrementer=new Incrementer(vector);
		/*
		 * A decrementer task
		 */
		Decrementer decrementer=new Decrementer(vector);
		
		/*
		 * Create and execute 100 incrementer and 100 decrementer tasks
		 */
		Thread threadIncrementer[]=new Thread[THREADS];
		Thread threadDecrementer[]=new Thread[THREADS];
		for (int i=0; i<THREADS; i++) {
			threadIncrementer[i]=new Thread(incrementer);
			threadDecrementer[i]=new Thread(decrementer);
			
			threadIncrementer[i].start();
			threadDecrementer[i].start();
		}
		
		/*
		 * Wait for the finalization of all the tasks
		 */
		for (int i=0; i<THREADS; i++) {
			try {
				threadIncrementer[i].join();
				threadDecrementer[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		/*
		 * Write the elements different from 0
		 */
		for (int i=0; i<vector.length(); i++) {
			if (vector.get(i)!=0) {
				System.out.println("Vector["+i+"] : "+vector.get(i));
			}
		}
		
		System.out.println("Main: End of the example");

    }
    
}
