/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo2;
    
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author pedroj
 */
public class Main implements Constantes {
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        System.out.println("Hilo(PRINCIPAL) ha iniciado su ejecución");
        
        ExecutorService ejecucion = Executors.newCachedThreadPool();
        PriorityBlockingQueue<Peticion> listaPeticiones = new PriorityBlockingQueue();
        ArrayList<Future<?>> listaTareas = new ArrayList();
        
        // Añadimos el Gestor de Peticiones
        GestorPeticiones gestor = new GestorPeticiones(listaPeticiones,listaTareas,ejecucion);
        Future<?> tareaGestor = ejecucion.submit(gestor);
        listaTareas.add(tareaGestor);
        
        // Añadimos los Clientes
        for ( int i = 0; i < TOTAL_CLIENTES; i++ ) {
            Cliente cliente = new Cliente("Cliente-"+i, listaPeticiones);
            Future<?> tareaCliente = ejecucion.submit(cliente);
            listaTareas.add(tareaCliente);
        }
        
        // Suspendemos la ejecución por un TIEMPO_MINIMO
        System.out.println("Hilo(PRINCIPAL) suspende su ejecución");
        TimeUnit.MINUTES.sleep(TIEMPO_MINIMO);
        
        System.out.println("Hilo(PRINCIPAL) finaliza las tareas activas");
        // Finalizamos las tareas
        for ( Future<?> tarea : listaTareas)
            tarea.cancel(true);
        
        // Esperamos hasta su finalizacion
        ejecucion.shutdown();
        ejecucion.awaitTermination(TIEMPO_MINIMO, TimeUnit.DAYS);
        
        System.out.println("Hilo(PRINCIPAL) ha finalizado su ejecución"); 
    }
    
}