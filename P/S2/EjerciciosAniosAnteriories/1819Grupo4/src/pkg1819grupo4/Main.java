/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo4;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jgr97
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static final float MIN_TIEMPO_ESPERA_ORDENACION=1f;
    public static final int TAM_TAREAS=10;
    public static final int TAM_ENTEROS=1000;
    public static final int MAX_ALEATORIO=9;
    
    public static void main(String[] args) throws InterruptedException {
        
        Thread [] hilos = new Thread[TAM_TAREAS];
        int [] enteros = new int [TAM_ENTEROS];
        Random generador = new Random();
        int indice = 0;
        
        for(int i=0; i<TAM_ENTEROS; i++){
            int num = generador.nextInt(MAX_ALEATORIO+1);
            
            enteros[i]=num;
        }
        
        for(int i=0; i<TAM_TAREAS; i++){
            hilos[i]=new Thread (new Tarea(enteros, indice, indice+100));
            indice=indice+100;
        }
        
        for(int i=0; i<TAM_TAREAS; i++){
            hilos[i].start();
        }
        
        int tEspera=20;
        
        System.out.println("El hilo principal se suspendera " + tEspera + " segundos.");
        TimeUnit.SECONDS.sleep(tEspera);
        
        
        for(int i=0; i<TAM_TAREAS; i++){
            hilos[i].interrupt();
        }
        
        for(int i=0; i<TAM_TAREAS; i++){
            hilos[i].join();
        }
        
        System.out.println("El hilo principal ha terminado.");
    }
    
}
