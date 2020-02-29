/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author pedroj
 */
public class Promotor implements Callable<List<Casa>> {
    private int id;
    private int num_etapas;

    public Promotor(int id, int num_etapas) {
        this.id = id;
        this.num_etapas = num_etapas;
    }
    
    
    

    @Override
    public List<Casa> call() throws Exception {
        List<Casa> listacasas = null;
        ExecutorService ejecutor = (ExecutorService) Executors.newCachedThreadPool();
        
        try{
            System.out.println("La tarea promotor: " + id + " ha comenzado.");
            listacasas = instalacionEtapas(ejecutor);
            System.out.println("La tarea promotor: " + id + " ha finalizado.");
        }catch(InterruptedException ex){
            System.out.println("La tarea promotor: " + id + " ha sido cancelada.");
        }
        finally{
            ejecutor.shutdown();
            ejecutor.awaitTermination(1, TimeUnit.DAYS);
        }
        
        return listacasas;
        
    }
    
    
    private List<Casa> instalacionEtapas(ExecutorService ejecutor) throws InterruptedException, ExecutionException{
        int num=0;
        List<Casa> casas_resultado = new ArrayList<>();
        int num_instaladores = Constantes.INSTALADORES;
        
        while(num<num_etapas){
            
            if(Thread.currentThread().isInterrupted()){
                throw new InterruptedException();
            }
            
            ArrayList<Instalador> instaladores = new ArrayList<>();
            
            for(int i=0; i<num_instaladores; i++){
                int num_sensores=Constantes.aleatorio.nextInt(Constantes.VARIACION)+Constantes.NUM_SENSORES;
                
                Instalador ins = new Instalador(i, num_sensores);
                
                instaladores.add(ins);
            }
            
            List<Future<Casa>> listacasas = ejecutor.invokeAll(instaladores);
            
            for(int i=0; i<num_instaladores; i++){
                Future <Casa> fcasa = listacasas.get(i);
                
                casas_resultado.add(fcasa.get());
            }
            
            num++;
        }
        
        return casas_resultado;
        
    }
    
}