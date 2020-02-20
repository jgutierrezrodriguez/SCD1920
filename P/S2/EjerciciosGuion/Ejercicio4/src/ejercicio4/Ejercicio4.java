/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio4;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author jgr97
 */
public class Ejercicio4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileSearch searcher=new FileSearch("..//..//..","build.xml");
		Thread thread=new Thread(searcher);
		
		// Starts the Thread
		thread.start();
		
		// Wait for ten seconds
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Interrupts the thread
		thread.interrupt();

    }
    
}
