/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo1;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 *
 * @author jgr97
 */
public class TareaCrearImpresion implements Runnable{
    private final ExecutorService ejecutor;
    private final ArrayList<Future<?>> lista;
    private int id;

    public TareaCrearImpresion(ExecutorService ejecutor, ArrayList<Future<?>> lista) {
        this.ejecutor = ejecutor;
        this.lista = lista;
        this.id = 0;
    }

    @Override
    public void run() {
        System.out.println("Tarea(CREACION) ha iniciado su ejecucion.");
        
        try{
            crearImpresion();
            
            System.out.println("Tarea(CREACION) ha finalizado su ejecucion.");
        }
        catch(InterruptedException ex){
            System.out.println("Tarea (CREACION)" + ex);
        }
    }
    
    private void crearImpresion() throws InterruptedException{
        if(Thread.currentThread().isInterrupted()){
            throw new InterruptedException("interrupcion solicitada");
        }
        else{
            TrabajoImpresion trabajo_impresion = new TrabajoImpresion(new Date());
            TareaImpresion tarea_impresion = new TareaImpresion(trabajo_impresion, "Tarea Impresion" + id);
            id++;
            TareaResultadoImpresion resultado= new TareaResultadoImpresion(tarea_impresion);
            ejecutor.submit(resultado);
            lista.add(resultado);
            
        }
    }
    
    
}
