/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio.pkg2;

/**
 *
 * @author jgr97
 */
public class Ejercicio2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        PricesInfo pricesInfo=new PricesInfo();

        Reader readers[]=new Reader[5];
        Thread threadsReader[]=new Thread[5];

        // Creates five readers and threads to run them
        for (int i=0; i<5; i++){
                readers[i]=new Reader(pricesInfo);
                threadsReader[i]=new Thread(readers[i]);
        }

        // Creates a writer and a thread to run it
        Writer writer=new Writer(pricesInfo);
        Thread threadWriter=new Thread(writer);

        // Starts the threads
        for (int i=0; i<5; i++){
                threadsReader[i].start();
        }
        threadWriter.start();

    }
    
}
