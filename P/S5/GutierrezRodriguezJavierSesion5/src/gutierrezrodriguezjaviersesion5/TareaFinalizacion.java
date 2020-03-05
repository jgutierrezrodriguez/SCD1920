/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion5;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 *
 * @author pedroj
 */
public class TareaFinalizacion implements Runnable {

    private List<Future<?>> lista;
    private CountDownLatch elemento_finalizacion;

    public TareaFinalizacion(List<Future<?>> lista, CountDownLatch elemento_finalizacion) {
        this.lista = lista;
        this.elemento_finalizacion = elemento_finalizacion;
    }
    
    @Override
    public void run() {
        System.out.println("Tarea Finalizacion ha iniciado su ejecucion.");
        
        for(int i=0; i<lista.size(); i++){
            lista.get(i).cancel(true);
        }
        
        System.out.println("Tarea Finalizacion ha finalizado su ejecucion");
        
        elemento_finalizacion.countDown();
    }
    
}