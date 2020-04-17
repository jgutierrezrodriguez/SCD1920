/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo1;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jgr97
 */
public class GestorPeticiones implements Constantes,Runnable {
    private final PriorityBlockingQueue<Peticion> listaPeticiones;
    private final ArrayList<Future<?>> listaTareas;
    private final ExecutorService ejecucion;

    public GestorPeticiones(PriorityBlockingQueue<Peticion> listaPeticiones, ArrayList<Future<?>> listaTareas, ExecutorService ejecucion) {
        this.listaPeticiones = listaPeticiones;
        this.listaTareas = listaTareas;
        this.ejecucion = ejecucion;
    }

    @Override
    public void run() {
        System.out.println("HILO(GestorPeticiones) inicia su ejecución");
        try {
            while ( true )
                ejecucion();
        } catch (InterruptedException ex) {
            System.out.println("HILO(GestorPeticiones) finaliza su ejecución");
        }
    }
    
    private void ejecucion() throws InterruptedException {
        Peticion peticion;
        
        // Comprobamos que no hay que finalizar
        if (Thread.interrupted())
            throw new InterruptedException();
        
        // Recogemos la primera peticion disponible y nos bloqueamos si no hay
        peticion = listaPeticiones.take();
        
        System.out.println("HILO(GestorPeticiones) procesa la " + peticion);
        Future<?> tareaEjecucion = ejecucion.submit(peticion);
        listaTareas.add(tareaEjecucion);
        
        // Simulamos el tiempo de procesamiento
        TimeUnit.SECONDS.sleep(TIEMPO_MINIMO);
    }
    
}