/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo1;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 *
 * @author jgr97
 */
public class TareaResultadoImpresion extends FutureTask<Resultado>{
    private String nombre;

    public TareaResultadoImpresion(Callable<Resultado> callable) {
        super(callable);
        this.nombre = ((TareaImpresion)callable).getNombre();
    }

    @Override
    protected void done() {
        if(isCancelled()){
            System.out.println("Tarea(" + nombre + ") no ha completado su ejecución");
        }
        else{
            System.out.println("Tarea(" + nombre + ") ha finalizado su ejecución");
        }
    }

    public String getNombre() {
        return nombre;
    }  
}
