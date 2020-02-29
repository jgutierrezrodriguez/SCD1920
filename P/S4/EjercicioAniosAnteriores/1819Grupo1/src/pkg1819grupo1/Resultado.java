/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo1;

import java.util.Date;

/**
 *
 * @author pedroj
 */
public class Resultado {
    private final String nombreTarea;
    private final Peticion peticion;
    private final Date fechaFinalizacion;

    public Resultado(String nombreTarea, Peticion peticion, Date fechaFinalizacion) {
        this.nombreTarea = nombreTarea;
        this.peticion = peticion;
        this.fechaFinalizacion = fechaFinalizacion;
    }

    @Override
    public String toString() {
        return "Resultado{" + "nombreTarea=" + nombreTarea + ", peticion=" + peticion 
                + ", fechaFinalizacion=" + fechaFinalizacion + '}';
    }
    
}