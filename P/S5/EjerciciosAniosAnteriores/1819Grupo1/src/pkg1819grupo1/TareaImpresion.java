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
public class TareaImpresion implements Callable<Resultado>{

    private final TrabajoImpresion trabajo;
    private final String nombre;

    public TareaImpresion(TrabajoImpresion trabajo, String nombre) {
        this.trabajo = trabajo;
        this.nombre = nombre;
    }
    
    @Override
    public Resultado call() throws Exception {
        Date fecha_finalizacion = trabajo.impresion();
        
        return new Resultado(nombre, trabajo, fecha_finalizacion);
    }

    public String getNombre() {
        return nombre;
    }
}
