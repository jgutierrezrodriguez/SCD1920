/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion9;

import static gutierrezrodriguezjaviersesion9.Constantes.CREAR_COMPONENTE;
import static gutierrezrodriguezjaviersesion9.Constantes.ESPERA_FINALIZACION;
import static gutierrezrodriguezjaviersesion9.Constantes.FABRICANTES;
import static gutierrezrodriguezjaviersesion9.Constantes.INICIO;
import static gutierrezrodriguezjaviersesion9.Constantes.SISTEMA;
import static gutierrezrodriguezjaviersesion9.Constantes.TIEMPO_ESPERA;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 *
 * @author pedroj
 */
public class GutierrezRodriguezJavierSesion9 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService ejecucionSistema;
        ExecutorService ejecucion;
        CompletionService<Componente> fabricacion;
        CountDownLatch esperaFinalizacion;
        List<Future<?>> listaTareas;
        Future<?> tarea;
        BlockingQueue<Ordenador> pedido;
        
        AtomicIntegerArray inventario = new AtomicIntegerArray(Constantes.COMPONENTES.length);
        AtomicIntegerArray contador = new AtomicIntegerArray(2); // Seria completado y no completado
        
        // Ejecución del hilo principal
        System.out.println("Ha iniciado la ejecución el Hilo(PRINCIPAL)");
        
        // Inicialización de las variables para la prueba
        ejecucionSistema = Executors.newScheduledThreadPool(SISTEMA);
        ejecucion = Executors.newFixedThreadPool(FABRICANTES);
        fabricacion = new ExecutorCompletionService(ejecucion);
        esperaFinalizacion = new CountDownLatch(ESPERA_FINALIZACION);
        listaTareas = new ArrayList();
        pedido = new LinkedBlockingQueue<>();
        
        // Se añade la tarea de fabricación de componentes que se repetirá cíclicamente
        CrearComponente crear = new CrearComponente(fabricacion,listaTareas, inventario);
        tarea = ejecucionSistema.scheduleAtFixedRate(crear, INICIO, CREAR_COMPONENTE, TimeUnit.SECONDS);
        listaTareas.add(tarea); // Se añade para la cancelación
        
        for(int i=0; i<Constantes.PROVEEDORES; i++){
        // Se añade la tarea del Proveedor para preparar el pedido de ordenadores
        Proveedor proveedor = new Proveedor(fabricacion,pedido,"Proveedor "+i, contador);
        tarea = ejecucionSistema.submit(proveedor);
        listaTareas.add(tarea); // Se añade para la cancelación
        }
        
        // Se crea la tarea de cancelación que se ejecutará pasado el TIEMPO_ESPERA
        // y finalizará las tareas que estén activas
        TareaFinalizacion fin = new TareaFinalizacion(listaTareas,esperaFinalizacion);
        ejecucionSistema.schedule(fin, TIEMPO_ESPERA, TimeUnit.MINUTES);
        System.out.println("HILO(Principal) Espera el tiempo establecido");
        esperaFinalizacion.await();
        
        // Cerramos los marcos de ejecucion
        ejecucionSistema.shutdown();
        ejecucion.shutdown();
        
        // Presentamos el resultado del pedido
        System.out.println("HILO(Principal) el pedido obtenido es \n______________");
        for( Ordenador ordenador : pedido )
            System.out.println(ordenador.toString());
        
        System.out.println("HILO(Principal) el inventario es de: ");
        System.out.println("\t- CPU: " + inventario.get(Constantes.TipoComponente.CPU.ordinal()));
        System.out.println("\t- MEMORIA: "+ inventario.get(Constantes.TipoComponente.MEMORIA.ordinal()));
        System.out.println("\t- PERIFERICO: "+ inventario.get(Constantes.TipoComponente.PERIFERICO.ordinal()));
        
        System.out.println("HILO(Principal) el contador de ordenadores es de: ");
        System.out.println("\t- Completados: " + contador.get(1));
        System.out.println("\t- No completados: " + contador.get(0));
        
        // Finalización del hilo principal
        System.out.println("Ha finalizado la ejecución el Hilo(PRINCIPAL)");
    }  
}