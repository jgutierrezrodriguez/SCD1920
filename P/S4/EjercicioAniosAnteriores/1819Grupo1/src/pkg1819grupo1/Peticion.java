/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo1;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author pedroj
 */
public class Peticion {
    private final Date fechaCreacion;
    private final int tiempoAtencion;
    
    // Constantes
    private final int MIN_TIEMPO = 2;
    private final int VARIACION_TIEMPO = 2;

    public Peticion(Date fecha) {
        Random generador = new Random();
        
        this.fechaCreacion = fecha;
        this.tiempoAtencion = MIN_TIEMPO + generador.nextInt(VARIACION_TIEMPO);
    }

    public Date getFecha() {
        return fechaCreacion;
    }
    
    /**
     * Simula el tiempo que lleva atender una petición
     * @return la fechaCreacion de la finalización de la petición
     * @throws InterruptedException 
     */
    public Date procesarPeticion() throws InterruptedException {
        
        TimeUnit.SECONDS.sleep(tiempoAtencion);
        
        return new Date();
    }

    @Override
    public String toString() {
        return "Peticion{" + "fechaCreacion=" + fechaCreacion + ", tiempoAtencion=" + tiempoAtencion + '}';
    }
}