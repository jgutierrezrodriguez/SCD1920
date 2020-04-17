/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion8;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 *
 * @author pedroj
 */
public class TareaFinalizacion implements Runnable {
    private final List<Future<?>> listaTareas;
    private final CountDownLatch esperaFinalizacion;

    public TareaFinalizacion(List<Future<?>> listaTareas, CountDownLatch esperaFinalizacion) {
        this.listaTareas = listaTareas;
        this.esperaFinalizacion = esperaFinalizacion;
    }


    @Override
    public void run() {
        
        System.out.println("Ha iniciado la ejecución la Tarea(FINALIZACION)");
        
        // Recorre la lista de tareas para solicitar su finalización
        for ( Future<?> tarea : listaTareas) 
            tarea.cancel(true);
        
        System.out.println("Ha finalizado la ejecución la Tarea(FINALIZACION)");
        // El programa principal puede presentar los resultados de las tareas
        esperaFinalizacion.countDown();
    }
}