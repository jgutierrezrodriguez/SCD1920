/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

/**
 *
 * @author jgr97
 */
public class Ejercicio2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
		 * Create an array to store the threads 
		 */
		Thread threads[]=new Thread[3];
		
		/*
		 * Launch three tasks
		 */
		for (int i=0; i<threads.length; i++) {
			TaskLocalRandom task=new TaskLocalRandom();
			threads[i]=new Thread(task);
			threads[i].start();
		}

    }
    
}
