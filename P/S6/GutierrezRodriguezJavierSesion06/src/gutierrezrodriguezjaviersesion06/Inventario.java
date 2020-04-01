/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion06;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author jgr97
 */
public class Inventario {
    private ArrayList<Integer> contadores;
    private ReentrantLock lock;

    public Inventario( ReentrantLock lock) {
        this.contadores = new ArrayList<>();
        this.lock = lock;
    }
    
    public void addSensor(Sensor sensor){
        
        int tipo_sensor = sensor.getSensor().ordinal();
        
        try{
            lock.lock();

            if(tipo_sensor>contadores.size())
                contadores.add(0);

            contadores.set(tipo_sensor, contadores.get(tipo_sensor) + 1);

            System.out.println("Se ha agregado un sensor de tipo: " + tipo_sensor);
        }
        finally{        
            lock.unlock();
        }
    }
    
    public int recuento(){
        int resultado = 0;
        
        for(int i=0; i<contadores.size(); i++){
            resultado = resultado + contadores.get(i);
        }
        
        return resultado;
    }

    @Override
    public String toString() {
        
        String resultado = "Inventario de sensores:";
        
        for(int i=0; i<contadores.size(); i++){
            resultado = resultado + " Tipo("+ i + "): " + contadores.get(i) + " ;";
        }
        
        return resultado;
        
    }
    
    
    
    
}
