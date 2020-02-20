/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio8;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jgr97
 */
public class UnsafeTask implements Runnable{

    private Date startDate;
    @Override
    public void run() {
         startDate=new Date();
        System.out.printf("Starting Thread: %s : %s\n",Thread.currentThread().getId(),startDate);
        try {
            TimeUnit.SECONDS.sleep((int)Math.rint(Math.random()*10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Thread Finished: %s : %s\n",Thread.currentThread().getId(),startDate);

    }
    
}
