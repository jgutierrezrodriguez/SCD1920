/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import static pkg1819grupo2.Main.PEDIDO_ORDENADORES;

/**
 *
 * @author jgr97
 */
public class TareaCrearOrdenador implements Runnable{
    
    private int [] componentesCreados;
    private ReentrantLock mutex;
    private CountDownLatch finTarea;
    private int ordenadoresCompletados;

    public TareaCrearOrdenador(int[] componentesCreados, ReentrantLock mutex, CountDownLatch finTarea) {
        this.componentesCreados = componentesCreados;
        this.mutex = mutex;
        this.finTarea = finTarea;
        this.ordenadoresCompletados = 0;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void crearOrdenador(){
        while(ordenadoresCompletados < PEDIDO_ORDENADORES){
            
        }
    }
    
    
    
    
}
