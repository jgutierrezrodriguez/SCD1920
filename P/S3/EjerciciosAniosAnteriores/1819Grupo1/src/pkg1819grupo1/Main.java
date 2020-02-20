/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo1;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author jgr97
 */
public class Main {

    public final static Random generador = new Random();
    
    // Constantes
    public static final int INDEX_QUESO = 0;
    public static final int INDEX_PAN = 1;
    public static final int INDEX_CARNE = 2;
    public static final String [] INGREDIENTES = {"QUESO","PAN","CARNE"};
    
    

    public static final int NUM_INGREDIENTES_HAMBURGUESA = 3;
    public static final int NUM_HAMBURGUESAS = 10;
    
    public static void main(String[] args) throws InterruptedException {
        
        Thread [] hiloIngredientes;
        TareaCrearHamburguesa tareaHamburguesa;
        Thread hiloHamburguesa;
        
        Boolean [] ingredientesCreados;
        ReentrantLock mutex;
        CyclicBarrier finHamburguesa;
        
        System.out.println("Ha iniciado la ejecución el Hilo(PRINCIPAL)");
        
        ingredientesCreados = new Boolean[NUM_INGREDIENTES_HAMBURGUESA];
        for(int i=0; i<ingredientesCreados.length; i++){
            ingredientesCreados[i]=false;
        }
        
        mutex=new ReentrantLock();
        tareaHamburguesa=new TareaCrearHamburguesa(ingredientesCreados, mutex);
        hiloHamburguesa=new Thread(tareaHamburguesa, "Preparacion Hamburguesa");
        
        finHamburguesa=new CyclicBarrier(NUM_INGREDIENTES_HAMBURGUESA, tareaHamburguesa);
        
        do{
            hiloIngredientes=new Thread[NUM_INGREDIENTES_HAMBURGUESA];
            for(int i=0; i<NUM_INGREDIENTES_HAMBURGUESA; i++){
                hiloIngredientes[i]=new Thread(new TareaCrearIngrediente(ingredientesCreados, mutex, i,finHamburguesa),"Crear ingrediente " + i);
            }
            
            for(int i=0;i<hiloIngredientes.length; i++){
                hiloIngredientes[i].start();
            }
            for(int i=0;i<hiloIngredientes.length; i++){
                hiloIngredientes[i].join();
            }
            
            System.out.println("Otra hamburguesa creada, ya tenemos "+tareaHamburguesa.getHamburguesasCreadas());
            finHamburguesa.reset();
            
        }
        while(tareaHamburguesa.getHamburguesasCreadas()<NUM_HAMBURGUESAS);
        
        System.out.println("Ha finalizado la ejecucion del hilo principal.");
        
    }
    
}
