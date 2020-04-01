/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion06;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author jgr97
 */
public class GutierrezRodriguezJavierSesion06 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("HILO PRINCIPAL ha iniciado su ejecucion.");
        
        ReentrantLock exm = new ReentrantLock();
        ArrayList<Sensor> almacen = new ArrayList<>();
        ForkJoinPool ejecucion = new ForkJoinPool();
        Inventario inventario = new Inventario(exm);
        
        
        
        for(int i=0; i<Constantes.INVENTARIO; i++){
            Sensor sensor = new Sensor(i, Constantes.TipoSensor.getSensor());
            almacen.add(sensor);
        }
        
        CalcularInventario tarea = new CalcularInventario(almacen, Constantes.INICIO, Constantes.INVENTARIO, inventario);
        
        ejecucion.invoke(tarea);
        
        System.out.println(inventario.toString());
        
        System.out.println("Recuento total: " + inventario.recuento());
        
        System.out.println("HILO PRINCIPAL ha finalizado su ejecucion.");

    }
    
}
