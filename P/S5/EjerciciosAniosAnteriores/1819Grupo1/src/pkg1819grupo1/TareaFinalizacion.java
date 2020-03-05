/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo1;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 *
 * @author jgr97
 */
public class TareaFinalizacion implements Runnable{
    
    private final ArrayList<Future<?>> lista;
    private final CountDownLatch count;

    public TareaFinalizacion(ArrayList<Future<?>> lista, CountDownLatch count) {
        this.lista = lista;
        this.count = count;
    }

    @Override
    public void run() {
        System.out.println("Tarea (FINALIZACION) ha iniciado su ejecucion.");
        
        for(int i=0; i<lista.size(); i++)
            lista.get(i).cancel(true);
        
        System.out.println("Tarea (FINALIZACION) ha finalizado su ejecucion.");
        count.countDown();
    }
    
    
    
}
