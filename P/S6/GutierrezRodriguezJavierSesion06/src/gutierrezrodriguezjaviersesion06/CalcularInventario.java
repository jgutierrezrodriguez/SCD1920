/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion06;

import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;

/**
 *
 * @author jgr97
 */


public class CalcularInventario extends RecursiveAction{
    
    private static final long serialVersionUID = 1L;
    
    private final ArrayList<Sensor> almacen;
    private final int inicio;
    private final int fin;
    private final Inventario inventario;

    public CalcularInventario(ArrayList<Sensor> almacen, int inicio, int fin, Inventario inventario) {
        this.almacen = almacen;
        this.inicio = inicio;
        this.fin = fin;
        this.inventario = inventario;
    }

    @Override
    protected void compute() {
        int num_elementos = fin - inicio;
        int mitad = num_elementos/2;
        System.out.println("Hilo(CALCULAR INVENTARIO) tareas pendientes de finalización: " + getQueuedTaskCount());
        
        if(num_elementos > Constantes.VALOR_BASE){
            ArrayList<CalcularInventario> tareas = new ArrayList<>();
            
            CalcularInventario tarea1 = new CalcularInventario(almacen, inicio, mitad, inventario);
            CalcularInventario tarea2 = new CalcularInventario(almacen, mitad, fin, inventario);
            
            tareas.add(tarea1);
            tareas.add(tarea2);
            
            invokeAll(tareas);
        }
        else{
            for(int i=inicio; i<fin; i++){
                inventario.addSensor(almacen.get(i));
            }
        }
    }
    
    
    
}
