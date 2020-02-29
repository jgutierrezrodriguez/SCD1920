/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo2;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jgr97
 */
public class TareaVentaEntradas implements Callable<String>{
    
    private static final int MIN_TIEMPO_VENTA=2;
    private static final int INC_TIEMPO_VENTA=2;
    private int entradas_disponibles;
    private int precio_entrada;
    private String nombre;

    public TareaVentaEntradas(int entradas_disponibles, int precio_entrada, String nombre) {
        this.entradas_disponibles = entradas_disponibles;
        this.precio_entrada = precio_entrada;
        this.nombre = nombre;
    }

    @Override
    public String call() throws Exception {
        String resultado = null;
        
        try{
            resultado=ventaEntradas();
        }
        catch(InterruptedException ex){
            System.out.println("La TAREA(" + nombre + ") ha sido cancelada");
        }
        
        return resultado;
    }
    
    private String ventaEntradas() throws InterruptedException{
        String resultado;
        int recaudado=0;
        for(int i=0; i<entradas_disponibles;i++){
            int tiempo = MIN_TIEMPO_VENTA + Main.generador.nextInt(INC_TIEMPO_VENTA);
            TimeUnit.SECONDS.sleep(tiempo);
            recaudado=recaudado+precio_entrada;
        }
        
        resultado = nombre + "-> Recaudado: "+recaudado;
        return resultado;
    }
    
    
    
    
    
}
