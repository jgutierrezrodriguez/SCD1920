/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo5;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import static pkg1819grupo5.Main.INC_TIEMPO_ESPERA;
import static pkg1819grupo5.Main.MIN_TIEMPO_ESPERA;

/**
 *
 * @author jgr97
 */
public class Tarea implements Runnable{
    private int [] datos;
    private int iInf, iSup;
    Random generador;
    private int num_sumas=0;
    private int suma=0;

    public Tarea(int[] datos, int iInf, int iSup) {
        this.datos = datos;
        this.iInf = iInf;
        this.iSup = iSup;
        generador = new Random();
    }

    @Override
    public void run(){
        try{
            System.out.println("Hilo " + Thread.currentThread().getName() + " ha comenzado.");
            
            suma();
            
            System.out.println("Hilo " + Thread.currentThread().getName() + " finalizado.");
        }
        catch(InterruptedException ex){
            System.out.println("Hilo " + Thread.currentThread().getName() + " interrumpido.");
        }
        finally{
            System.out.println("Hilo " + Thread.currentThread().getName() + ": Suma: " + suma);
        }
        
    }
    
    private void suma() throws InterruptedException{
        for(int i=iInf; i<iSup; i++){
            suma=suma + datos[i];
            num_sumas++;
            TimeUnit.SECONDS.sleep(MIN_TIEMPO_ESPERA+generador.nextInt(INC_TIEMPO_ESPERA+1));
            
            if(Thread.interrupted()){
                if(num_sumas<(((iSup-iInf)/2)+1))
                    throw new InterruptedException();
            }
        }
    }
    
}
