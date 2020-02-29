/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jgr97
 */
public class GutierrezRodriguezJavierSesion4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        System.out.println("El hilo PRINCIPAL COMIENZA.");
        
        ExecutorService ejecutor = (ExecutorService) Executors.newFixedThreadPool(Constantes.PROMOTORES);
        
        List<Promotor> lista_promotores = new ArrayList();
        List<Casa> lista_casas = new ArrayList<>();
        
        for(int i=0; i<Constantes.PROMOTORES; i++){
            Promotor promotor = new Promotor(i, Constantes.NUM_ETAPAS);
            
            lista_promotores.add(promotor);
        }
        
        lista_casas=ejecutor.invokeAny(lista_promotores);
        
        System.out.println("El hilo PRINCIPAL espera a que todas las tareas terminen.");
        ejecutor.shutdown();
        ejecutor.awaitTermination(1, TimeUnit.DAYS);
        
        System.out.println("El hilo PRINCIPAL muestra los resultados: ");
        
        for(int i=0; i<lista_casas.size(); i++){
            System.out.println(lista_casas.get(i).toString());
        }
        
    }
    
}
