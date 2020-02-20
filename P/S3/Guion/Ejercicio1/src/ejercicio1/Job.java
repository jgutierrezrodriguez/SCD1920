/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

/**
 *
 * @author jgr97
 */
public class Job implements Runnable{
    
    private PrintQueue queue;

    public Job(PrintQueue queue) {
        this.queue = queue;
    }
    
    @Override
    public void run() {
    System.out.printf("%s: Going to print a document\n",Thread.currentThread().getName());
    queue.printJob(new Object());
    System.out.printf("%s: The document has been printed\n",Thread.currentThread().getName());		
}

    
}
