/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion8;

import gutierrezrodriguezjaviersesion8.Constantes.TipoSensor;
import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author pedroj
 */
public class Sensor implements Delayed {
    private final int iD;
    private final TipoSensor sensor;
    private final Date disponible;

    // Constantes
    private final static int MENOR = -1;
    private final static int IGUAL = 0;
    private final static int MAYOR = 1;
    
    public Sensor(int iD, TipoSensor sensor) {
        this.iD = iD;
        this.sensor = sensor;
        this.disponible = new Date();
        
        // Añadimos el tiempo de fabricación 
        long tiempo = sensor.tiempoInstalacion();
        this.disponible.setTime(this.disponible.getTime() +
                                TimeUnit.MILLISECONDS.convert(tiempo, TimeUnit.SECONDS));
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

    @Override
    public long getDelay(TimeUnit unit) {
        // Tiempo que resta para la activación de la tarea
        Date actual = new Date();
        long diferencia = disponible.getTime() - actual.getTime();
        return unit.convert(diferencia, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        int resultado;
        
        // Comparamos los tiempos de retardoPeticion de dos tareas
        long diferencia = this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
        
        if ( diferencia < 0 )
            resultado = MENOR;
        else if ( diferencia > 0 )
            resultado = MAYOR;
        else 
            resultado = IGUAL;
        
        return resultado;
    }
}