/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion5;

import gutierrezrodriguezjaviersesion5.Constantes.TipoCasa;
import gutierrezrodriguezjaviersesion5.Constantes.TipoSensor;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author pedroj
 */
public class Instalador implements Callable<Casa> {
    private final String iD;
    private final int numSensores;
    private final Casa casa;

    public Instalador(String iD, int numSensores) {
        this.iD = iD;
        this.numSensores = numSensores;
        this.casa = new Casa(this.hashCode(), TipoCasa.getCasa());
    }
    
    @Override
    public Casa call() throws Exception {
        System.out.println("TAREA-" + iD + " Empieza la instalación de " + numSensores +
                           " sensores en la casa " + casa.getiD());
        
        try {
            for(int i = 0; i < numSensores; i++)
                instalarSensor(i);
            
            System.out.println("TAREA-" + iD + " Finaliza la instalación de " + numSensores +
                           " sensores en la casa " + casa.getiD());
        } catch (InterruptedException ex) {
            System.out.println("TAREA-" + iD + " CANCELA la instalación de " + numSensores +
                           " sensores en la casa " + casa.getiD());
        } 
        return casa;    
    }
    
    private void instalarSensor(int nSensor) throws InterruptedException {
        // Generamos un sensor aleatorio para instalarlo en la casa
        Sensor sensor = new Sensor(nSensor, TipoSensor.getSensor());
        if( casa.addSensor(sensor) )
            // Simulamos el tiempo de instalación
            TimeUnit.SECONDS.sleep(sensor.getTiempoInstalacion());
    }

    public String getiD() {
        return iD;
    }
}