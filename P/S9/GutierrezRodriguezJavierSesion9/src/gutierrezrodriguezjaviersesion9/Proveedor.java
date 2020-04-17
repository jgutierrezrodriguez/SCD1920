/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion9;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 *
 * @author pedroj
 */
public class Proveedor implements Runnable {
    private final CompletionService<Componente> fabricacion;
    private final BlockingQueue<Ordenador> pedido;
    private int numSerie;
    private String id;
    private List<Ordenador> listaTrabajo;
    private AtomicIntegerArray contadorOrdenadores;

    public Proveedor(CompletionService<Componente> fabricacion, BlockingQueue<Ordenador> pedido, String id, AtomicIntegerArray contadorOrdenadores) {
        this.id = id;
        this.numSerie = 1;
        this.fabricacion = fabricacion;
        this.pedido = pedido;
        this.listaTrabajo = new ArrayList<>();
        this.contadorOrdenadores = contadorOrdenadores;
    }
    
    @Override
    public void run() {
        System.out.println("TAREA-Proveedor empieza la preparación del pedido de ordenadores");
        
        try {
            
            while( true )
                prepararPedido();
            
        } catch ( InterruptedException ex ) {
            System.out.println("TAREA-Proveedor finaliza el pedido de ordenadores");
        } catch (ExecutionException ex) {
            System.out.println("TAREA-Proveedor ERROR en la fabricación");
        }
    }
    
    private void prepararPedido() throws InterruptedException, ExecutionException {
        Future<Componente> fabricado;
        Componente componente;
        Ordenador ordenador = null;
        Iterator it = listaTrabajo.iterator();
        boolean asignado = false;
        
        // Espera hasta tener un componente
        fabricado = fabricacion.take();
        componente = fabricado.get();
        
        // Asigna componente a un ordenador si es posible
        while ( it.hasNext() && !asignado ) {
            ordenador = (Ordenador) it.next();
            asignado = ordenador.addComponente(componente);
        }
        
        // Si no se ha podido asignar el componente se crea un nuevo ordenador
        // al pedido y se le asigna el componente
        if( !asignado ) {
            numSerie++;
            ordenador = new Ordenador();
            ordenador.setiD("Proveedor("+numSerie+")");
            ordenador.addComponente(componente);
            listaTrabajo.add(ordenador);
            contadorOrdenadores.getAndIncrement(Constantes.Estado.NO_COMPLETADO.ordinal());
        }
        
        if(ordenador.ordenadorCompleto()){
            pedido.add(ordenador);
            contadorOrdenadores.getAndDecrement(Constantes.Estado.NO_COMPLETADO.ordinal());
            contadorOrdenadores.getAndIncrement(Constantes.Estado.COMPLETADO.ordinal());
        }
    }
}
