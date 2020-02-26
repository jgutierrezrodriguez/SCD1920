/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion3;

import static gutierrezrodriguezjaviersesion3.Constantes.SENSORES;
import gutierrezrodriguezjaviersesion3.Constantes.TipoCasa;
import gutierrezrodriguezjaviersesion3.Constantes.TipoSensor;
import java.util.Arrays;

/**
 *
 * @author pedroj
 */
public class Casa {
    private final int iD;
    private final Sensor[][] sensoresHabitacion;
    private final TipoCasa tipo;

    public Casa(int iD, TipoCasa tipo) {
        this.iD = iD;
        this.tipo = tipo;
                
        sensoresHabitacion = new Sensor[tipo.getHabitaciones()][SENSORES.length];
        for(int i = 0; i < tipo.getHabitaciones(); i++)
            for(int j = 0; j < SENSORES.length; j++)
                sensoresHabitacion[i][j] = null;
    }

    public int getiD() {
        return iD;
    }

    public int getNumHabitaciones() {
        return tipo.getHabitaciones();
    }

    public TipoCasa getTipo() {
        return tipo;
    }
    
    public Sensor[][] getSensoresHabitacion() {
        return sensoresHabitacion;
    }
    
    /**
     * Añade un sensor a una habitación que no disponga de el
     * @param sensor que se añade a la habitación
     * @return true si el sensor se ha añadido false en otro caso
     */
    public boolean addSensor(Sensor sensor) {
        boolean asignado = false;
        
        TipoSensor tipoSensor = sensor.getSensor();
        int i = 0;
        while ( (i < tipo.getHabitaciones()) && !asignado ) 
            if( sensoresHabitacion[i][tipoSensor.ordinal()] != null ) {
                i++;
            } else {
                sensoresHabitacion[i][tipoSensor.ordinal()] = sensor;
                asignado = true;
            }
                
        return asignado;
    }

    @Override
    public String toString() {
        String resultado = "Casa[" + iD + ", "+ tipo + "]:";
        
        for(int i = 0; i < tipo.getHabitaciones(); i++)
            resultado = resultado + "\n\t Habitacion[" + (i+1) + "]: " + 
                    Arrays.toString(sensoresHabitacion[i]);
        
        return resultado;
    }
}