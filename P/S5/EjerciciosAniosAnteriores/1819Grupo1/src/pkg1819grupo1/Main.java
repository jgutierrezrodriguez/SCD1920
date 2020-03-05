/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo1;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jgr97
 */


public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static final int TAREAS_PRINCIPALES = 2;
    public static final int MIN_HILOS = 2;
    public static final int VARIACION_HILOS = 3;
    public static final int INICIO_IMPRESION = 0;
    public static final int REPETICION_IMPRESION = 2;
    public static final int FINALIZACION = 15;
    public static final int ESPERA_FINALIZACION = 1;
    public final static Random generador = new Random();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        int numHilos;
        ScheduledExecutorService ejecucionPrincipal;
        ExecutorService ejecucionTareas;
        ArrayList<Future<?>> lista;
        CountDownLatch esperafinalizacion;
        
        System.out.println("HILO (PRINCIPA) ha iniciado su ejecucion.");
        
        ejecucionPrincipal = Executors.newScheduledThreadPool(TAREAS_PRINCIPALES);
        
        numHilos = MIN_HILOS + generador.nextInt(VARIACION_HILOS);
        ejecucionTareas = Executors.newFixedThreadPool(numHilos);
        
        lista = new ArrayList<>();
        TareaCrearImpresion creaImpresion = new TareaCrearImpresion(ejecucionTareas, lista);
        ScheduledFuture<?> tareaCrear = ejecucionPrincipal.scheduleAtFixedRate(creaImpresion, 
                INICIO_IMPRESION, REPETICION_IMPRESION, TimeUnit.DAYS);
        lista.add(tareaCrear);
        
        
        esperafinalizacion = new CountDownLatch(ESPERA_FINALIZACION);
        TareaFinalizacion finalizacion = new TareaFinalizacion(lista, esperafinalizacion);
        
        System.out.println("HILO(PRINCIPAL) espera a la finalizacion.");
        esperafinalizacion.await();
        
        System.out.println("HILO (PRINCIPAL) muestra los resultados.");
        
        for(int i=0; i<lista.size(); i++){
            if(lista.get(i).isCancelled())
                System.out.println("El resultado de la tarea es: " + (lista.get(i)).get());
        }
        
        ejecucionTareas.shutdown();
        ejecucionPrincipal.shutdown();
        
        System.out.println("HILO (PRINCIPAL) ha finalizado su ejecucion.");
        
    }
    
}
