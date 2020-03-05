/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion5;

import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Future;

/**
 *
 * @author pedroj
 */
public class InstalarCasa implements Runnable {
    
    private final List<Future<?>> lista;
    private final CompletionService<Casa> ejecucion;
    private int id;

    public InstalarCasa(List<Future<?>> lista, CompletionService<Casa> ejecucion) {
        this.lista = lista;
        this.ejecucion = ejecucion;
        this.id = 0;
    }
    
    
    
    @Override
    public void run() {
        System.out.println("Tarea instalar casa ha iniciado su ejecucion.");
        
        try{
            instalacion();
            
            System.out.println("Tarea instalar casa ha finalizado su ejecucion");
        }
        catch(InterruptedException ex){
            System.out.println("Tarea instalar casa " + ex);
        }
        
    }
    
    private void instalacion() throws InterruptedException{
        Future<Casa> resultado;
        
        if(Thread.currentThread().isInterrupted()){
            throw new InterruptedException("interrupcion solicitada");
        }
        else{
            int num = Constantes.aleatorio.nextInt(Constantes.VARIACION) + Constantes.NUM_SENSORES;
            Instalador instalador = new Instalador("Instalador " + id, num);
            id++;
            resultado = ejecucion.submit(instalador);
            lista.add(resultado);
                  
        }
    }
    
}