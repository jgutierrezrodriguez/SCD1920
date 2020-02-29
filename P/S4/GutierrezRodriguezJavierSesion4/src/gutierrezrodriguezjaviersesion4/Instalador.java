/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion4;

import gutierrezrodriguezjaviersesion4.Casa;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author pedroj
 */
public class Instalador implements Callable<Casa> {

    private int id;
    private int num_sensores;

    public Instalador(int id, int num_sensores) {
        this.id = id;
        this.num_sensores = num_sensores;
    }
        
    @Override
    public Casa call() throws Exception {
        Constantes.TipoCasa tcasa = Constantes.TipoCasa.getCasa();
        
        Casa casa = new Casa(0, tcasa);
        
        try{
            System.out.println("El tarea Instalador: " + id + " ha comenzado.");
            
            realizarInstalacion(casa);
            
            System.out.println("El tarea Instalador: " + id + " ha finalizado.");
        }catch(InterruptedException ex){
            System.out.println("El tarea Instalador: " + id + " ha sido interrumpida.");
        }
      
        return casa;
    }
    
    private Casa realizarInstalacion(Casa casa) throws InterruptedException{
        int num_sensores_instalados=0;
        
        while(num_sensores_instalados<num_sensores){
            
            if(Thread.currentThread().isInterrupted())
                throw new InterruptedException();
            
            
            Sensor sensor =  new Sensor(num_sensores_instalados, Constantes.TipoSensor.getSensor());
            
            casa.addSensor(sensor);
            
            TimeUnit.SECONDS.sleep(sensor.getTiempoInstalacion());
            
            num_sensores_instalados++;
        }
        
        return casa;
        
    }
    
    
}