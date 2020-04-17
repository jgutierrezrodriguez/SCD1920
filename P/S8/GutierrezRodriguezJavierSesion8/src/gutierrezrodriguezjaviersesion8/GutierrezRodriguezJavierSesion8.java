/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion8;

import static gutierrezrodriguezjaviersesion8.Constantes.SISTEMA;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jgr97
 */
public class GutierrezRodriguezJavierSesion8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ScheduledExecutorService ejecucionSistema;
        ExecutorService ejecucionInstaladores;
        CountDownLatch esperaFinalizacion;
        List<Future<?>> listaTareas;
        Future<?> tarea;
        List<List<Casa>> listaCasas;
        
        DelayQueue<Sensor> almacen = new DelayQueue<Sensor>();
        
        ejecucionSistema = Executors.newScheduledThreadPool(SISTEMA);
        ejecucionInstaladores = Executors.newFixedThreadPool(Constantes.INSTALADORES);
        
        List<Thread> lista_tareas_fabricacion = new ArrayList<>();
        
        for(int i=0; i<Constantes.FABRICANTES; i++){
            FabricarSensor fabrica = new FabricarSensor("FabricaSensor " + i, almacen);
            Thread tar = new Thread(fabrica);
            tarea =  ejecucionSistema.scheduleAtFixedRate(tar, Constantes.INICIO, Constantes.FABRICACION, TimeUnit.DAYS);
        }
        
        
    }
    
}
