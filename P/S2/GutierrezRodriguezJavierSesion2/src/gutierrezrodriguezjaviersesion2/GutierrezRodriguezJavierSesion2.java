/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion2;

import static gutierrezrodriguezjaviersesion2.Constantes.TOTAL_INSTALADORES;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author pedroj
 */
public class GutierrezRodriguezJavierSesion2 {

    public static final int MIN_SENSORES=5;
    public static final int INC_SENSORES=2;
    public static final int MIN_CASAS=3;
    public static final int INC_CASAS=2;
    
    
    public static void main(String[] args) throws InterruptedException {
        Random generador = new Random();
        Instalador [] instaladores = new Instalador[TOTAL_INSTALADORES];
        Thread [] hilos = new Thread[TOTAL_INSTALADORES];
        
        System.out.println("El hilo principal ha comenzado.");
        
        for(int i=0; i<TOTAL_INSTALADORES; i++){
            ArrayList<Sensor> listaSensores = new ArrayList();
            
            int num_sensores=MIN_SENSORES + generador.nextInt(INC_SENSORES+1);
            
            for(int j=0; j<num_sensores; j++){
                listaSensores.add(new Sensor(j, Constantes.TipoSensor.getSensor()));
            }
            
            ArrayList<Casa> listaCasas=new ArrayList<>();
            int num_casas=MIN_CASAS + generador.nextInt(INC_CASAS+1);
            
            for(int j =0; j<num_casas; j++){
                listaCasas.add(new Casa(j, Constantes.TipoCasa.getCasa()));
            }

            instaladores[i]=new Instalador(i, listaSensores, listaCasas);
        }
        
        for(int i=0; i<TOTAL_INSTALADORES; i++){
            hilos[i]=new Thread(instaladores[i],"Hilo "+i);
        }
        
        System.out.println("Ejecutamos los hilos.");
        
        for(int i=0; i<TOTAL_INSTALADORES; i++){
            hilos[i].start();
        }
        
        System.out.println("El hilo principal espera "+Constantes.TIEMPO_ESPERA+" segundos");
        
        TimeUnit.SECONDS.sleep(Constantes.TIEMPO_ESPERA);
        
        for(int i=0; i<hilos.length; i++){
            if(hilos[i].isAlive()){
                hilos[i].interrupt();
            }
        }
        
        for(int i=0; i<hilos.length; i++){
            hilos[i].join();
        }
        
        System.out.println("El hilo principal ha terminado.");
        
    }
    
}