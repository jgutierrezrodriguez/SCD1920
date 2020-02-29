/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo1;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 *
 * @author jgr97
 */
public class TareaProcesarPeticion implements Callable<Resultado>{
    
    private final Peticion peticion;
    private final String nombre;

    public TareaProcesarPeticion(Peticion peticion, String nombre) {
        this.peticion = peticion;
        this.nombre = nombre;
    }

    @Override
    public Resultado call() throws Exception {
        Resultado resultado = null;
        Date fechafinalizacion ;
        
        try{
            fechafinalizacion = peticion.procesarPeticion();
            resultado = new Resultado(nombre, peticion, fechafinalizacion);
        }catch(InterruptedException ex){
            System.out.println("La tarea (" + nombre + ") ha sido cancelada.");
        }
        
        return resultado;
    }    
}
