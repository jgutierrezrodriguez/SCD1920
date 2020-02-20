/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo1;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jgr97
 */
public class Tarea implements Runnable{
 
    private final ArrayList<Recurso> lista_recursos;
    private final int numRecursos;
    private final Random generador;
    private static final int MIN_TIEMPO_CREACION=2;
    private static final int DIF_TIEMPO_CREACION=3;


    public Tarea(int numRecursos) {
        this.numRecursos = numRecursos;
        lista_recursos = new ArrayList<>();
        generador=new Random();
    }

    @Override
    public void run() {
        
        try{
            System.out.println("Hilo: "+Thread.currentThread().getName()+" empieza su ejecucion");

            for(int i=0; i<numRecursos; i++){
                creaRecurso(i);
            }
        }catch(InterruptedException ex){
                System.out.println("Se ha interrumpido la ejecución del HILO-" +
                    Thread.currentThread().getName());
        }finally{
            System.out.println("Hilo: " + Thread.currentThread().getName() + ". Recursos: "+lista_recursos);
        }
    }
    
    private Recurso creaRecurso(int n) throws InterruptedException{
        
        if(Thread.interrupted() && ((numRecursos/n)<(numRecursos/2)))
            throw new InterruptedException();
        
        Recurso re = new Recurso("Re"+n, new Date());
        int tiempo = generador.nextInt(DIF_TIEMPO_CREACION)+MIN_TIEMPO_CREACION;
        
        try{
            TimeUnit.SECONDS.sleep(tiempo);
        }catch(InterruptedException e){
            if((numRecursos/n)>(numRecursos/2))
                throw new InterruptedException();
        }
        
        return re;
    }

    
    
    
}
