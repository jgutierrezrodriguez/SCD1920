/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion3;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author pedroj
 */
public class GutierrezRodriguezJavierSesion3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        System.out.println("HILO PRINCIPAL: Ha iniciado su ejecucion.");
        
        int num_promotores = Constantes.aleatorio.nextInt(Constantes.VARIACION)+1;
        Thread [] hiloPromotores = new Thread[num_promotores];
        Phaser sincronizacion = new Phaser(num_promotores+1);
        
        
        for(int i=0; i<hiloPromotores.length; i++){
            int num_etapas=Constantes.aleatorio.nextInt(Constantes.VARIACION)+1;
            Promotor promotor = new Promotor(i,num_etapas , sincronizacion);
            
            hiloPromotores[i]=new Thread(promotor, "Promotor "+(i+1));
            
            hiloPromotores[i].start();
        }
        
        System.out.println("HILO PRINCIPAL: Se espera " + Constantes.TIEMPO_ESPERA + " segundos.");
        TimeUnit.SECONDS.sleep(Constantes.TIEMPO_ESPERA);
        
        for(int i=0; i<hiloPromotores.length; i++){
            hiloPromotores[i].interrupt();
        }
        
        for(int i=0; i<hiloPromotores.length; i++){
            hiloPromotores[i].join();
        }
        
        System.out.println("HILO PRINCIPAL: Ha finalizado su ejecucion.");
        
    }
    
}