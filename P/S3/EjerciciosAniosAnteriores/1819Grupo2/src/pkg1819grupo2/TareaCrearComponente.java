/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import pkg1819grupo2.Main.ComponenteOrdenador;
import static pkg1819grupo2.Main.TIPOS_COMPONENTES;

/**
 *
 * @author jgr97
 */
public class TareaCrearComponente implements Runnable{
    private int [] componentesCreados;
    private ReentrantLock mutex;
    
   private static final int MIN_TIEMPO=1;
   private static final int INC_TIEMPO=2;

    public TareaCrearComponente(int [] componentesCreados, ReentrantLock mutex) {
        this.componentesCreados = componentesCreados;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        try{
            System.out.println("Hilo " + Thread.currentThread().getName() + " ha comenzado su ejecucion.");
            
            crearComponente();
        }
        catch(InterruptedException ex){
            System.out.println("Hilo " + Thread.currentThread().getName() + " ha sido interrumpido.");
        }
    }
    
    private void crearComponente() throws InterruptedException{
        ComponenteOrdenador componente;
        while (true) {            
            int tiempo = Main.generador.nextInt(INC_TIEMPO) + MIN_TIEMPO;
            TimeUnit.SECONDS.sleep(tiempo);
            
            componente=TIPOS_COMPONENTES[Main.generador.nextInt(TIPOS_COMPONENTES.length)];
            
            mutex.lock();
            
            try{
                componentesCreados[componente.ordinal()]++;
            }
            finally{
                mutex.unlock();
            }
            
        }
    }
}
