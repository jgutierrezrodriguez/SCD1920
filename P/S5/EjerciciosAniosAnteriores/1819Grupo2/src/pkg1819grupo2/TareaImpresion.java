/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo2;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 *
 * @author pedroj
 */
public class TareaImpresion implements Callable<Resultado> {
    private final String nombre;
    private final TrabajoImpresion trabajoImpresion;

    public TareaImpresion(String nombre, TrabajoImpresion impresion) {
        this.nombre = nombre;
        this.trabajoImpresion = impresion;
    }

    @Override
    public Resultado call() throws Exception {
        Resultado resultado = null;
        Date fechaFinalizacion;
                
        try {
            fechaFinalizacion = trabajoImpresion.impresion();
            resultado = new Resultado(nombre,trabajoImpresion,fechaFinalizacion);
             
        } catch (InterruptedException ex) {
            System.out.println("TAREA(" + nombre + ") " + ex);
        } 
            
        return resultado;
    }

    public String getNombre() {
        return nombre;
    }
}