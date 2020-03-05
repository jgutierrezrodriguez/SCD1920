/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jgr97
 */
public class GutierrezRodriguezJavierSesion5 {

    /**
     * @param args the command line arguments
     */
    
    public final static int TAREAS_PRINCIPALES = 3;
    
    public static void mostrar(List<List<Casa>> lista_listacasas){
        int etapa=1;
        for(int i=0; i<lista_listacasas.size(); i++){
            System.out.println("*************** ETAPA " + etapa + " ***************");
            List<Casa> lista = lista_listacasas.get(i);
            
            for(int j=0; j<lista.size(); j++){
                System.out.println(lista.get(j).toString());
            }
            etapa++;
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        
        System.out.println("Hilo PRINCIPAL ha iniciado su ejecucion.");
        
        ScheduledExecutorService ejecuciosTareasPrincipales = Executors.newScheduledThreadPool(TAREAS_PRINCIPALES);
        ExecutorService ejecucionInstaladores = Executors.newFixedThreadPool(Constantes.INSTALADORES);
        CompletionService<Casa> ejecucionTareasInstaladores = new ExecutorCompletionService(ejecucionInstaladores);
        
        List <Future<?>> listaTareas = new ArrayList<>();
        InstalarCasa instalarCasa = new InstalarCasa(listaTareas, ejecucionTareasInstaladores);
        ScheduledFuture<?> tareaInstalarCasa = ejecuciosTareasPrincipales.scheduleAtFixedRate(instalarCasa, 
                Constantes.INICIO, Constantes.INSTALACION, TimeUnit.SECONDS);
        listaTareas.add(tareaInstalarCasa);
        
        List<List<Casa>> lista_listacasas = new ArrayList<>();
        Promotor promotor = new Promotor(ejecucionTareasInstaladores, lista_listacasas);
        ScheduledFuture<?> tareaPromotor = ejecuciosTareasPrincipales.scheduleAtFixedRate(promotor,
                Constantes.INICIO_PROMOTOR, Constantes.ESPERA_INSTALADOR, TimeUnit.SECONDS);
        listaTareas.add(tareaPromotor);
        
        CountDownLatch finalizacion = new CountDownLatch(Constantes.TIEMPO_ESPERA);
        TareaFinalizacion tareaFinalizacion = new TareaFinalizacion(listaTareas, finalizacion);
        ejecuciosTareasPrincipales.schedule(tareaFinalizacion, Constantes.TIEMPO_ESPERA, TimeUnit.MINUTES);
        
        System.out.println("Hilo PRINCIPAL espera a finalizacion.");
        finalizacion.await();
        
        ejecucionInstaladores.shutdown();
        ejecuciosTareasPrincipales.shutdown();
        
        System.out.println("Hilo PRINCIAPAL muestra el resultado de la ejecucion");
        mostrar(lista_listacasas);
        System.out.println("Hilo PRINCIPAL ha finalizado su ejecucion.");
    }
    
}
