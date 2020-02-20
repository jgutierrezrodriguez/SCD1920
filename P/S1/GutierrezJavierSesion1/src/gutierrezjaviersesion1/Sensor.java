/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezjaviersesion1;

import gutierrezjaviersesion1.GutierrezJavierSesion1.TipoSensor;

/**
 *
 * @author jgr97
 */
public class Sensor {
 
    private final int id;
    private final TipoSensor tiposensor;

    public Sensor(int id, TipoSensor tiposensor) {
        this.id = id;
        this.tiposensor = tiposensor;
    }

    public Sensor() {
        this.id = 0;
        this.tiposensor = null;
    }
    
    

    public int getId() {
        return id;
    }

    public TipoSensor getTiposensor() {
        return tiposensor;
    }

    @Override
    public String toString() {
        return "Sensor{" + "id=" + id + ", TipoSensor="+ tiposensor.toString() + '}';
    }
    
    
    
}
