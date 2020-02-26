/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo5;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import static pkg1819grupo5.Main.generador;

/**
 *
 * @author jgr97
 */
public class TareaCrearOrdenador implements Runnable{
    
    private final int[] componentesCreados;
    private final ReentrantLock exm;
    private final Phaser finTrabajo;
    private int ordenadoresCompletados;
    
    // Constantes
    private static final int TIEMPO_MINIMO = 2;
    private static final int INTERVALO_TIEMPO = 2;

    public TareaCrearOrdenador(int[] componentesCreados, ReentrantLock exm, Phaser finTrabajo) {
        this.componentesCreados = componentesCreados;
        this.exm = exm;
        this.finTrabajo = finTrabajo;
    }

    @Override
    public void run() {
        try {
            System.out.println("El HILO(" + Thread.currentThread().getName() + ") Comienza la ejecución");
            
            creaOrdenador();
            
            // Indicamos al hilo principal que hemos terminado el trabajo
            finTrabajo.arrive();
            
            System.out.println("El HILO(" + Thread.currentThread().getName() + ") Finaliza la ejecución");
        } catch (InterruptedException ex) {
            System.out.println("El HILO(" + Thread.currentThread().getName() + ") Ha sido cancelado");
        }
    }
    
    private void creaOrdenador() throws InterruptedException{
        while(ordenadoresCompletados<Main.PEDIDO_ORDENADORES){
            boolean disponible = false;
            
            while(!disponible){
                if(Thread.currentThread().isInterrupted()){
                    throw new InterruptedException();
                }
                
                exm.lock();
                try{
                    disponible=hayComponentes();
                }finally{
                    exm.unlock();
                }
            }
            
            int tiempo = TIEMPO_MINIMO + generador.nextInt(INTERVALO_TIEMPO);
            TimeUnit.SECONDS.sleep(tiempo);
            ordenadoresCompletados++;
            System.out.println("El HILO(" + Thread.currentThread().getName() + 
                    ") ha completado: " + ordenadoresCompletados + " ordenadores");
        }
    }
    
        private boolean hayComponentes() {
        int indice = 0;
        boolean disponible = true;
        
        while ( (indice < componentesCreados.length) && disponible ) {
            if ( componentesCreados[indice] == 0 ) 
                disponible = false;
            indice++;
        }
        
        if ( disponible )
            for ( int i = 0; i < componentesCreados.length; i++ )
                componentesCreados[i]--;
        
        return disponible;
    }
    
}
