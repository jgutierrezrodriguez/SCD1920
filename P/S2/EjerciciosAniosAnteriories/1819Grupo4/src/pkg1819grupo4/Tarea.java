/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo4;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import static pkg1819grupo4.Main.MIN_TIEMPO_ESPERA_ORDENACION;

/**
 *
 * @author jgr97
 */
public class Tarea implements Runnable{
    int [] datos;
    private final int iSup, iInf;
    Random generador;

    public Tarea(int[] datos, int iInf, int iSup) {
        this.datos = datos;
        this.iSup = iSup;
        this.iInf = iInf;
        generador = new Random();
    }

    @Override
    public void run() {
        
          try{
              System.out.println("El hilo " + Thread.currentThread().getName() + "ha comenzado su ejecucion."
                      + " Ordenada entre: " + iInf + " - " + iSup);
              
              bubbleSort();
              
              System.out.println("El hilo " + Thread.currentThread().getName() + " ha finalizado.");
          }catch(InterruptedException ex){
              System.out.println("El hilo " + Thread.currentThread().getName() + " ha sido interrumpido.");
          }
          finally{
              mostrarOrdenacion();
          }
        
    }
    
    private void bubbleSort() throws InterruptedException {  
        int temp = 0;
        
         for(int i=iInf; i < iSup; i++){  
             for(int j=iInf+1; j < (iSup-i); j++){  
                      if(datos[j-1] > datos[j]){  
                               
                             temp = datos[j-1];  
                             datos[j-1] =  datos[j];  
                             datos[j] = temp;  
                     }  

             }
                 
            float tEspera=MIN_TIEMPO_ESPERA_ORDENACION + generador.nextFloat();
            
            TimeUnit.SECONDS.sleep((long) tEspera);
            
            if(Thread.interrupted()){
                throw new InterruptedException();
            }
         }
    
    }
    
    private void mostrarOrdenacion(){
        String resultado="";
        
        for(int i=iInf; i<=iSup; i++){
            resultado=resultado + " " +datos[i];
        }
        
        System.out.println("Hilo " + Thread.currentThread().getName() + ". Datos ("+resultado+")");  
    }
    
}
