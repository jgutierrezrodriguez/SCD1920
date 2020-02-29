/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo1;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jgr97
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static final Random generador = new Random();
    public static final int NUM_TAREAS=4;
        public static final int TIEMPO_ESPERA = 1;
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService ejecucion = (ExecutorService) Executors.newCachedThreadPool();
        ArrayList<TareaGestorPeticiones> listagestores=new ArrayList<>();
        for(int i=0; i<NUM_TAREAS; i++){
            TareaGestorPeticiones tarea = new TareaGestorPeticiones("Gestor Peticiones " + i);
            
            listagestores.add(tarea);
        }
        
        String resultado = ejecucion.invokeAny(listagestores);
        
        System.out.println("Hilo(PRINCIPAL) esperamos a que todas las tareas finalicen");
        ejecucion.shutdownNow();
        ejecucion.awaitTermination(TIEMPO_ESPERA, TimeUnit.DAYS);
        
        System.out.println("Hilo(PRINCIPAL) el resultado de la primera tarea que finalizó es: " + resultado);
        System.out.println("Ha finalizado la ejecución el Hilo(PRINCIPAL)");
    }
    
}
