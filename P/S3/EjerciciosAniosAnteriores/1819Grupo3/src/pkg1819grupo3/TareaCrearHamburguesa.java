/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author jgr97
 */
public class TareaCrearHamburguesa implements Runnable{
    private final int[] ingredientessCreados;
    private final ReentrantLock exm;

    private int hamburguesas_creadas;
    
    // Constantes
    private static final int TIEMPO_MINIMO = 2;
    private static final int INTERVALO_TIEMPO = 2;
    Condition todos_ingredientes;

    public TareaCrearHamburguesa(int[] ingredientessCreados, ReentrantLock exm, Condition todos_ingredientes) {
        this.ingredientessCreados = ingredientessCreados;
        this.exm = exm;
        this.hamburguesas_creadas = 0;
        this.todos_ingredientes = todos_ingredientes;
    }

    public int getHamburguesas_creadas() {
        return hamburguesas_creadas;
    }

    @Override
    public void run() {
        try {
            System.out.println("TareaCrearHamburguesa Comienza la ejecución");
            
            creaHamburguesa();
            
 //           ingredientesBarrier.reset();
            
            System.out.println("TareaCrearHamburguesa Finaliza la ejecución");
        } catch (InterruptedException ex) {
            System.out.println("TareaCrearHamburguesa Ha sido cancelado");
        }
    }
    
    private void creaHamburguesa() throws InterruptedException{
        while(hamburguesas_creadas<Main.NUM_HAMBURGUESAS){
            if(Thread.currentThread().isInterrupted())
                throw new InterruptedException();
            
            exm.lock();
            
            try{
                while(!todosIngredientes()){
                    todos_ingredientes.await();
                }
                
                System.out.println("Hilo " + Thread.currentThread().getName() + " ha comenzado a preparar una hamburguesa.");
                int tiempo=Main.generador.nextInt(INTERVALO_TIEMPO)+TIEMPO_MINIMO;
                
                TimeUnit.SECONDS.sleep(tiempo);
                
                for(int i =0; i<ingredientessCreados.length; i++){
                    ingredientessCreados[i]=0;
                }
                
                todos_ingredientes.signalAll();
                
            }
            finally{
                exm.unlock();
            }
            
            System.out.println("Hilo " + Thread.currentThread().getName() + " ha hecho " + getHamburguesas_creadas() + " hamburguesas.");
            
            
            
        }
    }
    
        private boolean todosIngredientes(){
        for(int i=0; i<ingredientessCreados.length; i++){
            if(ingredientessCreados[i]==0)
                return false;
        }
        
        return true;
    }
    
    
}
