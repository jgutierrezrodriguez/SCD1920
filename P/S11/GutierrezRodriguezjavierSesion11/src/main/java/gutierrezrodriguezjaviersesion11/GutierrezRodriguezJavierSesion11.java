/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion11;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jgr97
 */
public class GutierrezRodriguezJavierSesion11 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("HILO(PRINCIPAL) inicia su ejecución");
        
        ExecutorService ejecucion = Executors.newCachedThreadPool();
        List<Future> listaFabricantes = new ArrayList<>();
        
        for(int i=0; i<Constantes.NUM_SENSORES; i++){
            Constantes.TipoSensor tipo = Constantes.TipoSensor.values()[i];
            Fabricante fabricante =  new Fabricante("FabSen"+i,tipo, Constantes.NUM_SENSORES);
            Future<?> tarea = ejecucion.submit(fabricante);
            listaFabricantes.add(tarea);
        }
        
        List<Future> listaPromotores = new ArrayList<>();
        for(int i=0; i<Constantes.PROMOTORES; i++){
            Promotor promo = new Promotor("Promo"+i, Constantes.NUM_CASAS);
            Future<?> tarea = ejecucion.submit(promo);
            listaPromotores.add(tarea);
        }
        
        ejecucion.shutdown();
        ejecucion.awaitTermination(Constantes.TIEMPO_ESPERA, TimeUnit.DAYS);
        
        for ( Future<?> tarea : listaFabricantes )
            tarea.cancel(true);
         
        for ( Future<?> tarea : listaPromotores )
            tarea.cancel(true);
        
        System.out.println("HILO(PRINCIPAL) finaliza su ejecución");
    }
    
}
