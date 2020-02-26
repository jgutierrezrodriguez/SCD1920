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
public class TareaCrearIngrediente implements Runnable{
    private final int [] ingredientessCreados;
    private final ReentrantLock exm;
    private int index_ingrediente;
    // Constantes
    private static final int TIEMPO_MINIMO = 1;
    private static final int INTERVALO_TIEMPO = 2;
    
    Condition todos_ingredientes;

    public TareaCrearIngrediente(int[] ingredientessCreados, ReentrantLock exm, int index_ingrediente, Condition todos_ingredientes) {
        this.ingredientessCreados = ingredientessCreados;
        this.exm = exm;
        this.index_ingrediente = index_ingrediente;
        this.todos_ingredientes = todos_ingredientes;
    }

    @Override
    public void run() {
        try {
            System.out.println("El HILO(" + Thread.currentThread().getName() + ") Comienza la ejecución");
            
            creaIngrediente();
            
        } catch (InterruptedException ex) {
            System.out.println("El HILO(" + Thread.currentThread().getName() + ") Finaliza la ejecución");
        }
    }
    
    private void creaIngrediente() throws InterruptedException{
        while (true) {            
            int tiempo=Main.generador.nextInt(INTERVALO_TIEMPO)+TIEMPO_MINIMO;
            TimeUnit.SECONDS.sleep(tiempo);
            
            
            exm.lock();
            
            try{
                System.out.println("El hilo " + Thread.currentThread().getName() + " prepara " + Main.INGREDIENTES[index_ingrediente]);
                
                if(todosIngredientes())
                    todos_ingredientes.signalAll();
                
                while(todosIngredientes())
                    todos_ingredientes.await();
            }
            finally{
                exm.unlock();
            }
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
