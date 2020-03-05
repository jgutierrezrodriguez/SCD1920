/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 *
 * @author pedroj
 */
public class Promotor implements Runnable {

    CompletionService<Casa> ejecucion;
    List<List<Casa>> lista_listacasas;

    public Promotor(CompletionService<Casa> ejecucion, List<List<Casa>> lista_listacasas) {
        this.ejecucion = ejecucion;
        this.lista_listacasas = lista_listacasas;
    }
    
    
    
    @Override
    public void run() {        
        System.out.println("Tarea Promotor ha iniciado su ejecucion.");
        
        try{
            procesarInstalaciones();
            
            System.out.println("Tarea Promotor ha finalizado su ejecucion.");
        }
        catch (InterruptedException | ExecutionException ex){
            System.out.println("Tarea Promotor " + ex);
        }
    }
    
    private void procesarInstalaciones() throws InterruptedException, ExecutionException{
        List<Casa> lista = new ArrayList<>();
        Future<Casa> resultado;
        
        while((resultado = ejecucion.poll()) != null){
            lista.add(resultado.get());
        }
        
        lista_listacasas.add(lista);
    }
    
}