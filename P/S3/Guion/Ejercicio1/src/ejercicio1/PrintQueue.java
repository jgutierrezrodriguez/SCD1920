/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author jgr97
 */
public class PrintQueue {
    
    private final Lock queue = new ReentrantLock();
    
    
    public void printJob(Object document){
        queue.lock();
        
        try {
            Long duration=(long)(Math.random()*10000);
            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n",Thread.currentThread().getName(),(duration/1000));
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queue.unlock();
        }

    }
}
