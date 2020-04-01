/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo4;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 *
 * @author jgr97
 */
public class Main {

    public final static Random generador = new Random();
    
    // Constantes
    public static final int NUM_ELEMENTOS = 1024;
    public static final int DATOS = 100;
    public static final int INICIO_DATOS = 0;
    public static final int NULO = 0;
    public static final int CASO_BASE = 2;
    public static final int DIVISION_TAREAS = 4;
    public static final int PAR = 0;
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Hilo(PRINCIPAL) ha iniciado su ejecución");
        
        // Creamos los datos para el ejercicio
        int[][] datos = new int[NUM_ELEMENTOS][NUM_ELEMENTOS];
        for ( int i = 0; i < NUM_ELEMENTOS; i++ ) 
            for ( int j = 0; j < NUM_ELEMENTOS; j++ )
                datos[i][j] = generador.nextInt(DATOS);
        
        // Creamos el marco de ejecución
        ForkJoinPool ejecucion = new ForkJoinPool();
        
        // Creamos la tarea y se añade al marco de ejecución
        // esperando a su finalización
        int valorBuscado = generador.nextInt(DATOS);
        Tarea tarea = new Tarea(datos,INICIO_DATOS,INICIO_DATOS,NUM_ELEMENTOS,valorBuscado);
        ejecucion.invoke(tarea);
        
        // Se presentan los resultados
        System.out.println("Hilo(PRINCIPAL) los datos son:");
        for ( int i = 0; i < NUM_ELEMENTOS; i++ )
            System.out.println(Arrays.toString(datos[i]));

        System.out.println("Hilo(PRINCIPAL) el resultado de la tarea es: " + tarea.get());
        
        System.out.println("Hilo(PRINCIPAL) ha finalizado su ejecución"); 
    }
    
}
