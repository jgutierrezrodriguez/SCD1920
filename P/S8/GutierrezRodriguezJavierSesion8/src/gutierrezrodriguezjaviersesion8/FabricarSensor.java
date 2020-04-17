/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion8;

import java.util.concurrent.DelayQueue;

/**
 *
 * @author pedroj
 */
public class FabricarSensor implements Runnable {
    
    private final String id;
    private final DelayQueue<Sensor> almacen;

    public FabricarSensor(String id, DelayQueue<Sensor> almacen) {
        this.id = id;
        this.almacen = almacen;
    }

    @Override
    public void run() {
        Constantes.TipoSensor tipo = Constantes.TipoSensor.getSensor();
        int identificador = almacen.size();
        Sensor nuevo_sensor = new Sensor(identificador, tipo);
        
        almacen.add(nuevo_sensor);   
    }
    
}