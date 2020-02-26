/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion3;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Phaser;

/**
 *
 * @author pedroj
 */
public class Promotor implements Runnable {
    
    Random generador = new Random();
    private int id;
    private int numero_etapas;
    Phaser sincronizacion;
    private ArrayList<Casa> arraycasas;
    
    String casas;

    public Promotor(int id, int numero_etapas, Phaser sincronizacion) {
        this.id = id;
        this.numero_etapas = numero_etapas;
        this.sincronizacion = sincronizacion;
        casas="";
        
        arraycasas=new  ArrayList<>();
    }
    
    

    @Override
    public void run() {
        try{
            System.out.println("Hilo: " + Thread.currentThread().getName() + " ha iniciado su ejecucion.");
            
            instalar();
            
            System.out.println("Hilo: " + Thread.currentThread().getName() + " ha finalizado su ejecucion.");
        }
        catch(InterruptedException ex){
            System.out.println("Hilo ( " + Thread.currentThread().getName() + " ) ha sido interrumpido.");
        }finally{
             System.out.println(Thread.currentThread().getName() + ": Casas instaladas: " + casas);
        }
    }
    
    
    private void instalar() throws InterruptedException{
        int num=0;
        
        while(num<numero_etapas){
            if(Thread.currentThread().isInterrupted())
                throw new InterruptedException();
            
            
            
            int num_instaladores = generador.nextInt(Constantes.VARIACION)+1;
            Thread [] hiloInstaladores = new Thread[num_instaladores];
            Phaser elemento_sincronizacion = new Phaser(num_instaladores+1);
            
            
            for(int i =0; i<hiloInstaladores.length; i++){
                int num_sensores= generador.nextInt(Constantes.VARIACION);
                Constantes.TipoCasa tipo = Constantes.TipoCasa.getCasa();
                Casa casa = new Casa(i, tipo);
                guardarCasa(casa);
                
                Instalador instalador = new Instalador(i, num_sensores, casa, elemento_sincronizacion);
                
                hiloInstaladores[i]=new Thread(instalador, "Instalador "+id +","+i);
                hiloInstaladores[i].start();
            }
            
            for(int i=0; i<hiloInstaladores.length; i++){
                hiloInstaladores[i].join();
            }
            
            sincronizacion.awaitAdvance(sincronizacion.getPhase());
            
            
            
            
        }
        
        sincronizacion.arriveAndDeregister();
        
        for(int i=0; i<arraycasas.size(); i++){
            guardarCasa(arraycasas.get(i));
        }
        
        
    }
    
    private void guardarCasa(Casa casa){
        casas=casas + casa.toString();
    }
}

