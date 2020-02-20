/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import pkg1819grupo2.Main.ComponenteOrdenador;
import static pkg1819grupo2.Main.PEDIDO_ORDENADORES;
import static pkg1819grupo2.Main.TIPOS_COMPONENTES;
import static pkg1819grupo2.Main.generador;

/**
 *
 * @author jgr97
 */
public class TareaCrearOrdenador implements Runnable{
    
    private int [] componentesCreados;
    private ReentrantLock mutex;
    private CountDownLatch finTarea;
    private int ordenadoresCompletados;
    
    private static final int TIEMPO_MINIMO = 2;
    private static final int INTERVALO_TIEMPO = 2;

    public TareaCrearOrdenador(int[] componentesCreados, ReentrantLock mutex, CountDownLatch finTarea) {
        this.componentesCreados = componentesCreados;
        this.mutex = mutex;
        this.finTarea = finTarea;
        this.ordenadoresCompletados = 0;
    }

    @Override
    public void run() {
        try {
            System.out.println("El HILO(" + Thread.currentThread().getName() + ") Comienza la ejecución");
            
            crearOrdenador();
            
            // Indicamos al hilo principal que hemos terminado el trabajo
            finTarea.countDown();
            
            System.out.println("El HILO(" + Thread.currentThread().getName() + ") Finaliza la ejecución");
        } catch (InterruptedException ex) {
            System.out.println("El HILO(" + Thread.currentThread().getName() + ") Ha sido cancelado");
        }
    }
    
    private void crearOrdenador() throws InterruptedException{
        while(ordenadoresCompletados < PEDIDO_ORDENADORES){
            boolean disponible = false;
            while ( !disponible ) {
                // Comprobamos si hay interrupción
                if ( Thread.currentThread().isInterrupted())
                    throw new InterruptedException();
                
                // Garantizamos el acceso en exclusion mutua
                mutex.lock();
                try {
                    disponible = hayComponentes();
                    
                    if ( disponible )
                        // Decontamos los compoentes para el ordenador
                        for( ComponenteOrdenador componente : TIPOS_COMPONENTES )
                            componentesCreados[componente.ordinal()]--;
                    
                } finally {
                    mutex.unlock();
                }
            }
            
            int tiempo = TIEMPO_MINIMO + generador.nextInt(INTERVALO_TIEMPO);
            TimeUnit.SECONDS.sleep(tiempo);
            ordenadoresCompletados++;
            System.out.println("El HILO(" + Thread.currentThread().getName() + 
                        ") ha completado: " + ordenadoresCompletados + " ordenadores");
        }
    }
    
    private boolean hayComponentes() throws InterruptedException {
        int indice = 0;
        boolean disponible = true;
        
        while ( (indice < componentesCreados.length) && disponible ) {
            if ( componentesCreados[indice] == 0 ) 
                disponible = false;
            indice++;
        }
        
        return disponible;
    }
    
    
    
    
}
