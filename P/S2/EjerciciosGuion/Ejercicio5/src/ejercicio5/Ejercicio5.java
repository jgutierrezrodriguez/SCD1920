/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio5;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jgr97
 */
public class Ejercicio5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        FileClock clock = new FileClock();
        Thread hilo = new Thread(clock);
        
        hilo.start();
        
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            Logger.getLogger(Ejercicio5.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        hilo.interrupt();
        
    }
    
}
