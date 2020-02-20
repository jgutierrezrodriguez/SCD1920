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
 * Esta clase representa a un recurso presente en el sistema
 * solo nos interesará el recurso por su nombre y fecha de creación.
 */
public class Recurso {
    private final String nombre;
    private final Date fecha;

    public Recurso(String nombre, Date fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Recurso{" + "nombre=" + nombre + ", fecha=" + fecha + '}';
    }
}