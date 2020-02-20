/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo1;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jgr97
 */
public class Main {

    public static final int MIN_TAREAS=10;
    public static final int INC_TAREAS=11;
    public static final int MINIMO_RECURSOS=20;
    public static final int INC_RECURSOS=11;
    public static final int TIEMPO_ENTRE_BLOQUES=1;
    public static final int TIEMPO_SUSPENSION=30;
    public static final int TAM_BLOQUE=5;
    
    
    
    public static void main(String[] args) throws InterruptedException {
        
        Random generador = new Random();
        Thread listahilos [];
        int numeroTareas = MIN_TAREAS + generador.nextInt(INC_TAREAS);
        listahilos = new Thread[numeroTareas];
        
        int tarea_actual=0;
        
        while(tarea_actual<numeroTareas){
            int bloque=0;
            
            while(tarea_actual<numeroTareas && bloque<TAM_BLOQUE){
                int numRecursos=MINIMO_RECURSOS + generador.nextInt(INC_RECURSOS);
                
                listahilos[tarea_actual]=new Thread(new Tarea(numRecursos),"Tarea ("+tarea_actual+")");
                
                
                listahilos[tarea_actual].start();
                tarea_actual++;
                bloque++;
            }
            
            TimeUnit.SECONDS.sleep(TIEMPO_ENTRE_BLOQUES);
        }
        
        System.out.print("Se suspende la ejecucion de los hilos por " + TIEMPO_SUSPENSION);
        TimeUnit.SECONDS.sleep(10);
        
        for (Thread listahilo : listahilos) {
            listahilo.interrupt();
        }
        
        for (Thread listahilo : listahilos) {
            listahilo.join();
        }
        
        System.out.println();
        System.out.print("Hilo principal finalizado");
        
        
        
    }
    
}
