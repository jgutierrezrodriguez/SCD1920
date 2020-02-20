/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo2;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author jgr97
 */
public class Main {
    
    public final static Random generador = new Random();
    
    // Constantes
    public static enum ComponenteOrdenador {CPU, MEMORIA, PERIFERICO};
    public static final ComponenteOrdenador[] TIPOS_COMPONENTES = ComponenteOrdenador.values();
    public static final int NUM_TAREAS_COMPONENTES = 3;
    public static final int NUM_TAREAS_ORDENADOR = 8;
    public static final int PEDIDO_ORDENADORES = 10;
    public static final int NUM_TAREAS_FINALIZADAS = 1;
    
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        Thread[] hilosComponentes;
        Thread[] hilosOrdenadores;
        int[] componentesCreados;
        ReentrantLock exm;
        CountDownLatch finTrabajo;
        
        System.out.println("Ha iniciado la ejecución el Hilo(PRINCIPAL)");
        
        // Creamos los recursos compartidos con las tareas
        componentesCreados = new int[TIPOS_COMPONENTES.length];
        for( int i = 0; i < componentesCreados.length; i++)
            componentesCreados[i] = 0;
        exm = new ReentrantLock();
        finTrabajo = new CountDownLatch(NUM_TAREAS_FINALIZADAS);
        
        // Creamos las tareas para la creación de componentes 
        hilosComponentes = new Thread[NUM_TAREAS_COMPONENTES];
        for( int i = 0; i < NUM_TAREAS_COMPONENTES; i++ ){
            TareaCrearComponente tareaComponente = new TareaCrearComponente(componentesCreados, exm);
            hilosComponentes[i] = new Thread(tareaComponente, "Componente-" + i);
            hilosComponentes[i].start();
        } 
        
        // Creamos las tareas para la creación de ordenadores
        hilosOrdenadores = new Thread[NUM_TAREAS_ORDENADOR];
        for( int i = 0; i < NUM_TAREAS_ORDENADOR; i++ ) {
            TareaCrearOrdenador tareaOrdenador = new TareaCrearOrdenador(componentesCreados, exm, finTrabajo);
            hilosOrdenadores[i] = new Thread(tareaOrdenador, "Ordenador-" + i);
            hilosOrdenadores[i].start();
        }
        
        // Sincronización hasta que una tarea complete los ordenadores solicitados
        System.out.println("Hilo(PRINCIPAL) espera hasta que una tarea complete el pedido de ordenadores");
        finTrabajo.await();
        
        // Se cancelan el resto de las tareas
        System.out.println("Hilo(PRINCIPAL) cancela el resto de las tareas");
        for( Thread hilo : hilosComponentes )
            hilo.interrupt();
        for( Thread hilo : hilosOrdenadores )
            hilo.interrupt();
        
        // Espera a que finalicen todas las tareas
        System.out.println("Hilo(PRINCIPAL) espera a la finalización de las tareas");
        for( Thread hilo : hilosComponentes )
            hilo.join();
        for( Thread hilo : hilosOrdenadores )
            hilo.join();
        
        System.out.println("Hilo(PRINCIPAL) Los componentes creados que aún quedan: ");
        for( ComponenteOrdenador componente : TIPOS_COMPONENTES ) {
            System.out.println(componente + ": " + componentesCreados[componente.ordinal()]);
        }
        
        System.out.println("Ha finalizado la ejecución el Hilo(PRINCIPAL)");
    }
    
}
