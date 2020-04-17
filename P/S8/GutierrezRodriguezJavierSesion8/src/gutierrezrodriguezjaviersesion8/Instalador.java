/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 *
 * @author pedroj
 */
public class Instalador implements Runnable {
    
    private final String id;
    private final int num_sensores;
    private final DelayQueue<Sensor> almacen;
    private final LinkedBlockingDeque<List<Casa>> casas_instaladas;

    public Instalador(String id, int num_sensores, DelayQueue<Sensor> almacen, LinkedBlockingDeque<List<Casa>> casas_instaladas) {
        this.id = id;
        this.num_sensores = num_sensores;
        this.almacen = almacen;
        this.casas_instaladas = casas_instaladas;
    }


    @Override
    public void run() {
        System.out.println( id + " ha comenzado su ejecucion");
        
        
        try{
            instalacion();
            System.out.println( id + " ha finalizado su ejecucion");
        }
        catch(InterruptedException ex){
            System.out.println( id + " ha sido interrumpida");
        }
    }
    
    
    private void instalacion() throws InterruptedException{
        if(Thread.interrupted()){
            throw new InterruptedException();
        }
        
        List<Casa> lista_instalacion = new ArrayList<>();
        int contador = 0;
        boolean instaladoencasa = false;
        
        while(contador<num_sensores){
            Sensor a_instalar = almacen.poll();
            
            for(int i=0; i<lista_instalacion.size() && instaladoencasa == false; i++){
                instaladoencasa = lista_instalacion.get(i).addSensor(a_instalar);
            }
            
            if(instaladoencasa == false){
                while(instaladoencasa == false){
                    Constantes.TipoCasa tipo = Constantes.TipoCasa.getCasa();
                    Casa casa = new Casa(contador, tipo);
                    
                    instaladoencasa = casa.addSensor(a_instalar);
                    
                    if(instaladoencasa == true){
                        lista_instalacion.add(casa);
                    }
                }
            }
           
           contador++; 
        }
        
        casas_instaladas.add(lista_instalacion);
        
    }
}