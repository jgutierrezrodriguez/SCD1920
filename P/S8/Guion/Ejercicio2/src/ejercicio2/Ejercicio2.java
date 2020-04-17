/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jgr97
 */
public class Ejercicio2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque<String> list=new LinkedBlockingDeque<>(3);
		
		Client client=new Client(list);
		Thread thread=new Thread(client);
		thread.start();
		
		for (int i=0; i<5 ; i++) {
			for (int j=0; j<3; j++) {
				String request=list.take();
				System.out.printf("Main: Request: %s at %s. Size: %d\n",request,new Date(),list.size());
			}
			TimeUnit.MILLISECONDS.sleep(300);
		}
		
		System.out.printf("Main: End of the program.\n");

    }
    
}
