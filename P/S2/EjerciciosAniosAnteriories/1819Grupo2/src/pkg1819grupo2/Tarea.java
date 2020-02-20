/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo2;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jgr97
 */
public class Tarea implements Runnable{
    
    private final float [] arrayfloat;
    private final int iInf, iSup;
    Random generador;

    public Tarea(float[] arrayfloat, int iInf, int iSup) {
        this.arrayfloat = arrayfloat;
        this.iInf = iInf;
        this.iSup = iSup;
        generador=new Random();
    }

    @Override
    public void run() {
        
        try{
            
            System.out.println("La ejecucion del hilo " + Thread.currentThread() + " ha comenzado.");
            
            for(int i =iInf;i<=iSup; i++){
            calculoElemento(i);
            }
        }catch (InterruptedException ex){
            System.out.println("La ejecucion del hilo "+ Thread.currentThread().getName() + " ha sido interrumpida.");
        }
        finally{
            System.out.println("Ha finalizado la ejecucion del hilo "+Thread.currentThread().getName() + ". Suma de elementos: " + sumaElementos());
        }
        
    }
    
    private void calculoElemento(int pos) throws InterruptedException{
        
        if(Thread.interrupted() && sumaElementos()>arrayfloat.length)
            throw new InterruptedException();
        
        float valor=generador.nextFloat() + generador.nextFloat();
        
        arrayfloat[pos] = valor;
        
        try {
            TimeUnit.SECONDS.sleep((long) valor);
        } catch (InterruptedException ex) {
           if(sumaElementos()>arrayfloat.length)
               throw new InterruptedException();
        }
        
        
        
    }
    
    private float sumaElementos(){
        float tam=arrayfloat.length;
        float suma=0;
        
        for(int i =0; i<tam; i++){
            suma=suma+arrayfloat[i];
        }
        
        return suma;
        
    }
    
    
    
    
    
}
