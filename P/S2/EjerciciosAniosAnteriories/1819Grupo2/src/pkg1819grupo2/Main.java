/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo2;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jgr97
 */
public class Main {

    public static final int TAM_VECTOR_FLOATS=1000;
    public static final int TAM_VECTOR_TAREAS=10;
    public static final int MIN_TIEMPO_ESPERA=60;
    public static final int INC_TIEMPO_ESPERA=30;
            
    public static void main(String[] args) {
        float [] vectorfloats = new float[TAM_VECTOR_FLOATS];
        Tarea [] tareas = new Tarea[TAM_VECTOR_TAREAS];
        Thread [] hilos = new Thread[TAM_VECTOR_TAREAS];
        Random generador = new Random();
        
        
        System.out.println("El hilo principal ha comenzado");
        
        for(int i=0; i<TAM_VECTOR_FLOATS; i++){
            vectorfloats[i] = 0;
        }
        
        
        int indice=0;
        
        for(int i=0; i<TAM_VECTOR_TAREAS; i++){
            tareas[i]=new Tarea(vectorfloats, indice, indice+100);
            hilos[i]=new Thread(tareas[i], "Tarea ("+i+")");
            indice = indice + 100;
        }
        
        for(int i=0; i<TAM_VECTOR_TAREAS;i++){
            hilos[i].start();
        }
        
        //int tiempoEspera = MIN_TIEMPO_ESPERA + generador.nextInt(INC_TIEMPO_ESPERA+1);
        int tiempoEspera=2;
        System.out.println("El hilo principal se va suspender " + tiempoEspera + " segundos.");
        
        try {
            TimeUnit.SECONDS.sleep(tiempoEspera);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        for(int i=0; i<TAM_VECTOR_TAREAS; i++){
            hilos[i].interrupt();
        }
        
        for(int i=0; i<TAM_VECTOR_TAREAS; i++){
            try {
                hilos[i].join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
        System.out.println("El hilo principalm ha finalizado.");
        
    }
    
}
