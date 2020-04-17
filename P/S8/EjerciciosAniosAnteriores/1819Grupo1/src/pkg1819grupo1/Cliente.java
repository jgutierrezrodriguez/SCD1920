/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo1;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jgr97
 */
public class Cliente implements Constantes,Runnable {
    private final String idCliente;
    private final PriorityBlockingQueue<Peticion> listaPeticiones;
    private final ConcurrentLinkedDeque<Pedido> listaPedidos;

    public Cliente(String idCliente, PriorityBlockingQueue<Peticion> listaPeticiones) {
        this.idCliente = idCliente;
        this.listaPeticiones = listaPeticiones;
        this.listaPedidos = new ConcurrentLinkedDeque();
    }


    
    @Override
    public void run() {
        int totalPeticiones;
        
        totalPeticiones = MINIMO_PETICIONES + generador.nextInt(VARIACION_PETICIONES);
        
        System.out.println("HILO(" + idCliente + ") comienza su ejecución para " +
                totalPeticiones + " peticiones");
        
        try {
            crearPeticiones(totalPeticiones);
            
            procesarPedidos(totalPeticiones);
            
            System.out.println("HILO(" + idCliente + ") finaliza su ejecución");
        } catch (InterruptedException ex) {
            System.out.println("HILO(" + idCliente + ") se ha interrumpido " + ex);
        } 
    }
    
    private void crearPeticiones(int totalPeticiones) throws InterruptedException {
        int prioridad;
        Peticion peticion;
        Prioridad prioridadPeticion;
        Date inicioPeticion;
        
        for (int i = 0; i < totalPeticiones; i++ ) {
            
            // Comprobamos que no hay que finalizar
            if (Thread.interrupted())
                throw new InterruptedException();
        
            prioridad = generador.nextInt(GENERAR_PRIORIDAD);
            if ( prioridad < PRIORIDAD_ALTA )
                prioridadPeticion = Prioridad.ALTA;
            else if ( prioridad < PRIORIDAD_BAJA )
                prioridadPeticion = Prioridad.BAJA;
            else
                prioridadPeticion = Prioridad.NORMAL;
            
            inicioPeticion = new Date();
            peticion = new Peticion(prioridadPeticion,idCliente,listaPedidos, inicioPeticion);
            listaPeticiones.add(peticion);
        }
    }
    
    private void procesarPedidos(int totalPeticiones) throws InterruptedException {
        Pedido pedido;
        int tiempoProcesado;
        
        for (int i = 0; i < totalPeticiones; i++ ) {
            // Comprobamos que no hay que finalizar
            if (Thread.interrupted())
                throw new InterruptedException();
            
            // Simulamos el tiempo de procesamiento
            tiempoProcesado = TIEMPO_MINIMO + generador.nextInt(VARIACION_TIEMPO);
            TimeUnit.SECONDS.sleep(tiempoProcesado);
            
            try { 
                pedido = listaPedidos.removeFirst();
                System.out.println("HILO(" + idCliente + ") " + pedido);
            } catch (NoSuchElementException ex) {
                // Eliminamos la primera ocurrencia de las peticiones del cliente
                Peticion peticion = new Peticion(idCliente);
                if ( listaPeticiones.remove(peticion) )
                    System.out.println("HILO(" + idCliente + ") elimina una petición"); 
            }
        }
    }
}