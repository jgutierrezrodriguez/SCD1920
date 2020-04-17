/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion8;

import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;

/**
 *
 * @author pedroj
 */
public class Promotor implements Runnable {
    
    private final String id;
    private final ExecutorService ejecutor;
    private final DelayQueue<Sensor> almacen;
    private final LinkedBlockingDeque<List<Casa>> casas_instaladas;

    public Promotor(String id, ExecutorService ejecutor, DelayQueue<Sensor> almacen, LinkedBlockingDeque<List<Casa>> casas_instaladas) {
        this.id = id;
        this.ejecutor = ejecutor;
        this.almacen = almacen;
        this.casas_instaladas = casas_instaladas;
    }


    @Override
    public void run() {
        System.out.println(id + " ha iniciado su ejecucion.");
        
        ejecucion();
        
        System.out.println(id + " ha finalizado su ejecucion.");
    }
    
    
    private void ejecucion(){
        int num_sensores = Constantes.aleatorio.nextInt(Constantes.VARIACION) + Constantes.NUM_SENSORES;
        
        Instalador instalador = new Instalador("Instalador-"+id, num_sensores, almacen, casas_instaladas);
        
        ejecutor.submit(instalador);
    }
}