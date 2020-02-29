/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo2;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static pkg1819grupo2.Main.generador;

/**
 *
 * @author jgr97
 */
public class TareaSalaCine implements Callable<Resultado>{
    private final String nombre;
    private final ArrayList<TareaVentaEntradas> listaTareas;
    
    // Constantes
    private final int MIN_TAREAS = 5;
    private final int VARIACION_TAREAS = 5;
    private final int MIN_HILOS = 4;
    private final int VARIACION_HILOS = 2;
    private final int TIEMPO_ESPERA = 1;
    private final int NUM_ENTRADAS = 10;
    private final int PRECIO_ENTRADA = 10;
    private final int NOMBRE = 0;
    private final int RECAUDADO = 1;

    public TareaSalaCine(String nombre) {
        this.nombre = nombre;
        listaTareas = new ArrayList();
    }
    
    @Override
    public Resultado call() throws Exception {
        String resultadoEjecucion;
        Resultado resultado = null;
        
        System.out.println("La TAREA(" + nombre + ") comienza su ejecución");
        
        // Creamos el marco de ejecución de las tareas
        int numHilos = MIN_HILOS + generador.nextInt(VARIACION_HILOS);
        ExecutorService ejecucion = Executors.newFixedThreadPool(numHilos);
        System.out.println("La TAREA(" + nombre + ") puede tener un máximo de " + numHilos + 
                " ventas al mismo tiempo");
        
        try { 
            resultadoEjecucion = crearTareas(ejecucion);
        
            resultado = obtenerResultado(resultadoEjecucion);
                         
        } catch (InterruptedException | ExecutionException ex) {
            System.out.println("La TAREA(" + nombre + ") ha cancelado su ejecución");
        } finally {
            // Finalizamos el marco de ejecución y esperamos a que finalicen las tareas
            ejecucion.shutdown();
            ejecucion.awaitTermination(TIEMPO_ESPERA, TimeUnit.DAYS);
            System.out.println("La TAREA(" + nombre + ") finaliza su ejecución");
        }
       
        return resultado;
    }
    
    
    private String crearTareas(ExecutorService ejecucion) throws InterruptedException, ExecutionException {
        int numTareas = MIN_TAREAS + generador.nextInt(VARIACION_TAREAS);
        
        for ( int i = 0; i < numTareas; i++ ) {
            
            TareaVentaEntradas tareaVenta = new TareaVentaEntradas(nombre + "-Ventanilla-" + i,
                                                NUM_ENTRADAS, PRECIO_ENTRADA);
            listaTareas.add(tareaVenta);
        }
        
        System.out.println("La TAREA(" + nombre + ") tiene " + numTareas + " ventanillas de venta"); 
        
        String resultadoEjecucion = ejecucion.invokeAny(listaTareas);
        
        return resultadoEjecucion;
    }
    
    
    private Resultado obtenerResultado(String resultadoEjecucion) {
        String[] elementosResultado;
        
        elementosResultado = resultadoEjecucion.split(SEPARADOR);
        
        String nombreTarea = elementosResultado[NOMBRE];
        int importeRecaudado = Integer.parseInt(elementosResultado[RECAUDADO]);
        
        Resultado resultado = new Resultado(nombreTarea, importeRecaudado);
        
        
        return resultado;
    }
}
