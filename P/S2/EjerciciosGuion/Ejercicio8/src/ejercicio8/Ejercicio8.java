/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio8;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author jgr97
 */
public class Ejercicio8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //UnsafeTask
//        
//         UnsafeTask task=new UnsafeTask();
//		
//        // Throw three Thread objects
//        for (int i=0; i<3; i++){
//            Thread thread=new Thread(task);
//            thread.start();
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }


         SafeTask task=new SafeTask();
		
        // Throw three Thread objects
        for (int i=0; i<3; i++){
            Thread thread=new Thread(task);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    
}
