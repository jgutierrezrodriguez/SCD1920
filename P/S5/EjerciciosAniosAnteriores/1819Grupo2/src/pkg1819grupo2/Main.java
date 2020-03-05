/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorCompletionService;
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
    public final static Random generador = new Random();
    
    // Constantes
    public static final int TAREAS_PRINCIPALES = 3;
    public static final int MIN_HILOS = 2;
    public static final int VARIACION_HILOS = 3;
    public static final int INICIO_IMPRESION = 0;
    public static final int REPETICION_IMPRESION = 2;
    public static final int INICIO_RESULTADO = 4;
    public static final int REPETICION_RESULTADO = 4;
    public static final int FINALIZACION = 15;
    public static final int ESPERA_FINALIZACION = 1;
    
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        int numHilos;
        ScheduledExecutorService ejecucionPrincipal;
        ExecutorService ejecucionTareas;
        List<Future<?>> listaTareas;
        CompletionService<Resultado> servicioEjecucion;
        CountDownLatch esperaFinalizacion;
        
        // Creamos el marco de ejecución planificada para las tareas principales
        ejecucionPrincipal = Executors.newScheduledThreadPool(TAREAS_PRINCIPALES);
        
        // Creamos el marco de ejercución donde se añadirán las tareas
        // que queremos ejecutar y el número máximo de trabajos de impresión concurrentes
        numHilos = MIN_HILOS + generador.nextInt(VARIACION_HILOS);
        ejecucionTareas = Executors.newFixedThreadPool(numHilos);
        servicioEjecucion = new ExecutorCompletionService(ejecucionTareas);
        
        listaTareas = new ArrayList();
        TareaCrearImpresion crearImpresion = new TareaCrearImpresion(servicioEjecucion, listaTareas);
        ScheduledFuture<?> tareaCrear = ejecucionPrincipal.scheduleAtFixedRate(crearImpresion, 
                                                INICIO_IMPRESION, REPETICION_IMPRESION, TimeUnit.SECONDS);
        listaTareas.add(tareaCrear);
        
        TareaProcesarResultado procesarResultado = new TareaProcesarResultado(servicioEjecucion);
        ScheduledFuture<?> tareaResultado = ejecucionPrincipal.scheduleAtFixedRate(procesarResultado, 
                                                INICIO_RESULTADO, REPETICION_RESULTADO, TimeUnit.SECONDS);
        listaTareas.add(tareaResultado);
        
        esperaFinalizacion = new CountDownLatch(ESPERA_FINALIZACION);
        TareaFinalizacion finalizacion = new TareaFinalizacion(listaTareas, esperaFinalizacion);
        ejecucionPrincipal.schedule(finalizacion, FINALIZACION, TimeUnit.SECONDS);
        
        System.out.println("Hilo(PRINCIPAL) espera a la finalización");
        esperaFinalizacion.await();
         
        ejecucionTareas.shutdown();
        ejecucionPrincipal.shutdown();
        System.out.println("Hilo(PRINCIPAL) ha finalizado su ejecución");  
        
    }
    
}
