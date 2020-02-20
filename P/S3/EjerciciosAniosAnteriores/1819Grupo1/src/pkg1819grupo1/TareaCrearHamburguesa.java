/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import static pkg1819grupo1.Main.INGREDIENTES;
import static pkg1819grupo1.Main.generador;

/**
 *
 * @author jgr97
 */
public class TareaCrearHamburguesa implements Runnable{
    private static final int TIEMPO_MINIMO = 2;
    private static final int INTERVALO_TIEMPO = 2;
    
    private final Boolean [] ingredientesCreados;
    private final ReentrantLock mutex;
    
    private int hamburguesasCreadas;

    public TareaCrearHamburguesa(Boolean[] ingredientesCreados, ReentrantLock mutex) {
        this.ingredientesCreados = ingredientesCreados;
        this.mutex = mutex;
        
        hamburguesasCreadas=0;
    }

    public int getHamburguesasCreadas() {
        return hamburguesasCreadas;
    }

    @Override
    public void run() {
        try{
            System.out.println("Hilo " + Thread.currentThread().getName() + " ha comenzado su ejecucion.");
            
            crearHamburguesa();
            
            System.out.println("Hilo " + Thread.currentThread().getName() + " ha finalizado su ejecucion.");
        }
        catch(InterruptedException ex){
            System.out.println("Hilo " + Thread.currentThread().getName() + " ha sido interrumpido.");
        }
        
    }
    
    private void crearHamburguesa() throws InterruptedException{
        if(Thread.currentThread().isInterrupted())
            throw new InterruptedException();
        
        mutex.lock();
        try{
            for(int i=0; i<ingredientesCreados.length; i++){
                System.out.println("\t en ingrediente "+INGREDIENTES[i]+" creado ?"+ingredientesCreados[i]);
            }
            
            int tiempo = generador.nextInt(INTERVALO_TIEMPO)+TIEMPO_MINIMO;
            TimeUnit.SECONDS.sleep(tiempo);
            this.hamburguesasCreadas++;
        }
        catch(InterruptedException ex){
            throw new InterruptedException();
        }
        finally{
            mutex.unlock();
        }

        System.out.println("Hilo " + Thread.currentThread().getName() + " ha creado " + hamburguesasCreadas + " hamburguesas");
    }
    
    
    
    
}
