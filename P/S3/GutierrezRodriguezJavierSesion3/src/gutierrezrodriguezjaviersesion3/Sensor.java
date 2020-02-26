/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion3;

import gutierrezrodriguezjaviersesion3.Constantes.TipoSensor;

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

    Sensor(Sensor sensor, TipoSensor sensor0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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