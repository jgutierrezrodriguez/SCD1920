/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion9;

import gutierrezrodriguezjaviersesion9.Constantes.TipoComponente;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 *
 * @author pedroj
 */
public class CrearComponente implements Runnable {
    private final CompletionService<Componente> fabricacion;
    private final List<Future<?>> listaTareas;
    private int numSerie;
    private AtomicIntegerArray inventario;

    public CrearComponente(CompletionService<Componente> fabricacion, List<Future<?>> listaTareas, AtomicIntegerArray inventario) {
        this.fabricacion = fabricacion;
        this.listaTareas = listaTareas;
        this.numSerie = 1;
        this.inventario = inventario;
    }

    @Override
    public void run() {
        System.out.println("TAREA-CrearComonente se fabrica la " + numSerie + "º pieza");
        
        try {
            
            fabricar();
            
        } catch ( InterruptedException ex ) {
            System.out.println("TAREA-CrearComonente CANCELA la fabricación");
        }
            
    }
    
    private void fabricar() throws InterruptedException {
        Future<Componente> tarea;
        
        if( Thread.currentThread().isInterrupted() )
            throw new InterruptedException();
        
        Fabricante fabricante = new Fabricante("Fabricante("+numSerie+")", 
                                                TipoComponente.getComponente(), 
                inventario);
        numSerie++;
        
        tarea = fabricacion.submit(fabricante);
        listaTareas.add(tarea);
    }
}
