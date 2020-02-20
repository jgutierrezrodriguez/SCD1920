/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo3;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import static pkg1819grupo3.Main.INC_TIEMPO_ESPERA_CALCULO;
import static pkg1819grupo3.Main.MIN_TIEMPO_ESPERA_CALCULO;

/**
 *
 * @author jgr97
 */




public class Tarea implements Runnable{
    
    private float [] datos;
    private int indI, indS;
    Random generador;

    public Tarea(float[] datos, int indI, int indS) {
        this.datos = datos;
        this.indI = indI;
        this.indS = indS;
        
        generador=new Random();
    }

    @Override
    public void run() {
        
        
        
        
    }
    
    
    private float calculoElemento(int indice){
        float resultado=1;
        float dividendo=1,divisor;
        
        if(indice!=0){
            if((indice%2)==1){
                dividendo=-1;
            }
            
            divisor=(indice*2)+1;
            
            resultado=dividendo/divisor;
            
        }
        
        int tiempo= MIN_TIEMPO_ESPERA_CALCULO + generador.nextInt(INC_TIEMPO_ESPERA_CALCULO);
        
        try {
            TimeUnit.SECONDS.sleep(tiempo);
        } catch (InterruptedException ex) {
            
        }
        
        return resultado;  
    }
    
    
    

    
}
