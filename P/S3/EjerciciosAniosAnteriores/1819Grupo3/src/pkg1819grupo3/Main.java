/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo3;

import java.util.Random;
import java.util.concurrent.locks.Condition;
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
        Thread[] hilosIngredientes;
        TareaCrearHamburguesa tareaHamburguesa;
        Thread hiloHamburguesa;
        int [] ingredientessCreados;
        ReentrantLock exm;
        Condition todos_ingredientes;
        
        System.out.println("Hilo PRINCIPAL ha iniciado su ejecucion.");
        
        ingredientessCreados = new int[NUM_INGREDIENTES_HAMBURGUESA];
        for( int i = 0; i < ingredientessCreados.length; i++)
            ingredientessCreados[i] = 0;
        
        exm=new ReentrantLock();
        todos_ingredientes= exm.newCondition();
        
        tareaHamburguesa=new TareaCrearHamburguesa(ingredientessCreados, exm, todos_ingredientes);
        hiloHamburguesa=new Thread(tareaHamburguesa,"TareaCreaHamburguesas");
        
        hilosIngredientes = new Thread[NUM_INGREDIENTES_HAMBURGUESA];
        for( int i = 0; i < NUM_INGREDIENTES_HAMBURGUESA; i++ ){
            TareaCrearIngrediente tareaComponente = new TareaCrearIngrediente(ingredientessCreados, exm,i,todos_ingredientes);
            hilosIngredientes[i] = new Thread(tareaComponente, "Ingrediente-" + i);
            hilosIngredientes[i].start();
        }
        
        hiloHamburguesa.start();
        hiloHamburguesa.join();
        
        System.out.println("Ha finalizado la preparación de hamburguesas el Hilo(PRINCIPAL)");
        
        for(int i =0; i<hilosIngredientes.length; i++){
            hilosIngredientes[i].interrupt();
        }
        
        for(int i =0; i<hilosIngredientes.length; i++){
            hilosIngredientes[i].join();
        }
        
        System.out.println("Ha finalizado la ejecución el Hilo(PRINCIPAL)");
        
    }
    
}
