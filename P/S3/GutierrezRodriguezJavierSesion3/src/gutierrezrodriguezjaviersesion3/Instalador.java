/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion3;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author pedroj
 */
public class Instalador implements Runnable {
    
    private int id;
    private int numero_sensores;
    private Casa casa;
    private Phaser sincronizacion;

    public Instalador(int id, int numero_sensores, Casa casa, Phaser sincronizacion) {
        this.id = id;
        this.numero_sensores = numero_sensores;
        this.casa = casa;
        this.sincronizacion = sincronizacion;
    }
    
    @Override
    public void run() {
        
        try{
            System.out.println("Hilo ( " + Thread.currentThread().getName() + " ) ha iniciado su ejecucion.");
            
            instalarSensores();
                        
            System.out.println("Hilo ( " + Thread.currentThread().getName() + " ) ha finalizado su ejecucion.");
        }catch(InterruptedException ex){
            System.out.println("Hilo ( " + Thread.currentThread().getName() + " ) ha sido interrumpido.");
        } 
        
    }
    
    private void instalarSensores() throws InterruptedException{
        int num_sensores_instalados=0;
        
        while(num_sensores_instalados<numero_sensores){
            if(Thread.currentThread().isInterrupted())
                throw new InterruptedException();
            
            
            
            Sensor sensor =  new Sensor(num_sensores_instalados, Constantes.TipoSensor.getSensor());
            
            casa.addSensor(sensor);
            
            TimeUnit.SECONDS.sleep(sensor.getTiempoInstalacion());
            
            num_sensores_instalados++;
            
            sincronizacion.awaitAdvance(sincronizacion.getPhase());
            
            
        }
        
        sincronizacion.arriveAndDeregister();
    }
    
}