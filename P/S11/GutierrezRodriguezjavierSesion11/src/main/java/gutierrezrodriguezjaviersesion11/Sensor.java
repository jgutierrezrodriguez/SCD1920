/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion11;

import gutierrezrodriguezjaviersesion11.Constantes.TipoSensor;

/**
 *
 * @author pedroj
 */
public class Sensor {
    private final int iD;
    private final TipoSensor sensor;

    public Sensor(int iD, TipoSensor sensor) {
        this.iD = iD;
        this.sensor = sensor;
    }

    public int getiD() {
        return iD;
    }

    public TipoSensor getSensor() {
        return sensor;
    }

    public int getTiempoInstalacion() {
        return sensor.tiempoInstalacion();
    }

    @Override
    public String toString() {
        return "Sensor{" + "iD=" + iD + ", sensor=" + sensor + '}';
    }
}
