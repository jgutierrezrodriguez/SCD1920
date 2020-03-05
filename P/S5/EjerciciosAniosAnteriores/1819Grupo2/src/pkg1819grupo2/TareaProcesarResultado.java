/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo2;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 *
 * @author jgr97
 */
public class TareaProcesarResultado implements Runnable{
    CompletionService<Resultado> ejecutor;

    public TareaProcesarResultado(CompletionService<Resultado> ejecutor) {
        this.ejecutor = ejecutor;
    }

    @Override
    public void run() {
        System.out.println("Tarea (PROCESADO) ha iniciado su ejecucion.");
        
        try{
            procesarResultado();
            
            System.out.println("Tarea (PROCESADO) ha finalizado su ejecucion.");
        }catch (InterruptedException | ExecutionException ex){
            System.out.println("Tarea (PROCESADO) " + ex);
        }
    }
    
    private void procesarResultado() throws InterruptedException, ExecutionException{
        Future<Resultado> resultado;
        
        while((resultado = ejecutor.poll()) != null){
            System.out.println("Tarea (PROCESADO) resultado procesado: " + resultado.get());
        }
    }
}
