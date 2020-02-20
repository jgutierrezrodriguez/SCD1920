/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import static pkg1819grupo1.Main.generador;

/**
 *
 * @author jgr97
 */


public class TareaCrearIngrediente implements Runnable{
    private final Boolean ingredientesCreados[];
    private final ReentrantLock mutex;
    private final int indexIngrediente;
    
    private static final int TIEMPO_MINIMO=1;
    private static final int TIEMPO_INC=2;
    
    CyclicBarrier barrier;

    public TareaCrearIngrediente(Boolean[] ingredientesCreados, ReentrantLock mutex, int indexIngrediente, CyclicBarrier barrier) {
        this.ingredientesCreados = ingredientesCreados;
        this.mutex = mutex;
        this.indexIngrediente = indexIngrediente;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        
        try{
            System.out.println("El hilo " + Thread.currentThread().getName() + " ha comenzado su ejecucion.");
            crearIngrediente();
        }
        catch (InterruptedException ex){
            System.out.println("El hilo " + Thread.currentThread().getName() + " ha sido interrumido.");
        }
    }
    
    private void crearIngrediente() throws InterruptedException{
        
        int tiempo = generador.nextInt(TIEMPO_INC) + TIEMPO_MINIMO;
        TimeUnit.SECONDS.sleep(tiempo);
        
        mutex.lock();
        
        try{
            ingredientesCreados[indexIngrediente]=true;
        }
        finally{
            mutex.unlock();
        }
        
        try{
            barrier.await();
        }
        catch(BrokenBarrierException ex){
            ex.printStackTrace();
            throw new InterruptedException();
        }
        
    }
    
}
