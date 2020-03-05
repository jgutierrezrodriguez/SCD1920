/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Future;

/**
 *
 * @author jgr97
 */
public class TareaCrearImpresion implements Runnable{
    private final CompletionService <Resultado> ejecutor;
    private final List<Future<?>> lista_tareas;
    private int id;

    public TareaCrearImpresion(CompletionService<Resultado> ejecutor, List<Future<?>> lista_tareas) {
        this.ejecutor = ejecutor;
        this.lista_tareas = lista_tareas;
        this.id = 0;
    }

    @Override
    public void run() {
        System.out.println("Tarea (IMPRESION) ha iniciado su ejecucion.");
        
        try{
            creaImpresion();
            
            System.out.println("Tarea (IMPRESION) ha finalizado su ejecucion.");
        }
        catch(InterruptedException ex){
            System.out.println("Tarea (IMPRESION) " + ex);
        }
    }
    
    private void creaImpresion() throws InterruptedException{
        Future<Resultado> resultado;
        
        if(Thread.currentThread().isInterrupted()){
            throw new InterruptedException("Interrupcion solicitada");
        }
        else{
            TrabajoImpresion trabimp = new TrabajoImpresion(new Date());
            TareaImpresion tareaimp = new TareaImpresion("Tarea impresion " + id, trabimp);
            id++;
            
            resultado = ejecutor.submit(tareaimp);
            lista_tareas.add(resultado);
        }
    }
    
    
}
