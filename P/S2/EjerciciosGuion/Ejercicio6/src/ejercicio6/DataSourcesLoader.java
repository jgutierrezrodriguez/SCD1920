/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio6;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jgr97
 */
public class DataSourcesLoader implements Runnable{

    @Override
    public void run() {

        System.out.printf("Begining data sources loading: %s\n",new Date());
        
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        System.out.printf("Data sources loading has finished: %s\n",new Date());

    }
    
    
    
}
