/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import static pkg1819grupo1.Main.generador;

/**
 *
 * @author jgr97
 */
public class TareaGestorPeticiones implements Callable<String>{
    private static final int MIN_HILOS=2;
    private static final int INC_HILOS=2;
    private static final int MIN_TAREAS=5;
    private static final int INC_TAREAS=5;
    
    private String nombre;
    private ArrayList<TareaProcesarPeticion> listaTareas;

    public TareaGestorPeticiones(String nombre) {
        this.nombre = nombre;
        this.listaTareas = new ArrayList<>();
    }

    @Override
    public String call() throws Exception {
        List<Future<Resultado>> listaresultados;
        String resultado = null;
        
        System.out.println("La TAREA(" + nombre + ") comienza su ejecución");
        
        int numHilos = MIN_HILOS + generador.nextInt(INC_HILOS);
        ExecutorService ejecutor = Executors.newFixedThreadPool(numHilos);
        System.out.println("La TAREA(" + nombre + ") puede procesar un máximo de " + numHilos + 
                " peticiones al mismo tiempo");
        
        try{
            listaresultados=crearTareas(ejecutor);
            
            resultado=obtenerResultado(listaresultados);
            
            System.out.println("La TAREA(" + nombre + ") finaliza su ejecución");
        }catch(InterruptedException ex){
            System.out.println("La TAREA(" + nombre + ") ha sido cancelada.");
        }
        finally{
            ejecutor.shutdown();
        }
        
        return resultado;
    }
    
    private List<Future<Resultado>> crearTareas(ExecutorService ejecutor) throws InterruptedException{
        int numTareas = MIN_TAREAS + Main.generador.nextInt(INC_TAREAS);
        
        for(int i=0; i<numTareas; i++){
            Peticion pet = new Peticion(new Date());
            TareaProcesarPeticion tarea = new TareaProcesarPeticion(pet, nombre + "-Procesar-"+i);
            
            listaTareas.add(tarea);  
        }
        
        System.out.println("La TAREA(" + nombre + ") tiene que procesar " + numTareas + " peticiones"); 
        
        List<Future<Resultado>> listaResultados = ejecutor.invokeAll(listaTareas);
        
        return listaResultados;
    }
    
    private String obtenerResultado(List<Future<Resultado>> resultadoEjecucion) {
        return "La TAREA(" + nombre + ") ha completado: " + resultadoEjecucion.size() +
                " peticiones";
    }
    
    
}
