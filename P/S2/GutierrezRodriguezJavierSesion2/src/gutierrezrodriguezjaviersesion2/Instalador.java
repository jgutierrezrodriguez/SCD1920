/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion2;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author pedroj
 */
public class Instalador implements Runnable {
    
    
    private final int id;
    private final ArrayList<Sensor> listaSensores;
    private final ArrayList<Casa> listaCasas;
    private final ArrayList<Sensor> sensores_no_instalados;

    public Instalador(int id, ArrayList<Sensor> listaSensores, ArrayList<Casa> listaCasas) {
        this.id = id;
        this.listaSensores = listaSensores;
        this.listaCasas = listaCasas;
        this.sensores_no_instalados = new ArrayList<>();
    }
    

    @Override
    public void run() {
        
        try{
            System.out.println( Thread.currentThread().getName() + " ha comenzado.");
            
            encontrarCasa();
            
            System.out.println( Thread.currentThread().getName() + " ha finalizado.");
        }
        catch(InterruptedException ex){
            System.out.println( Thread.currentThread().getName() + " ha sido interrumpido.");
        }
        finally{
            System.out.println( Thread.currentThread().getName() + ". Casas: (" + estadoCasa() + ")");
            System.out.println( Thread.currentThread().getName() + ". Sensores no instalados: (" + sensoresSinInstalar()+ ")");
        }
        
    }
    
    
    private void encontrarCasa() throws InterruptedException{
        boolean insertado =false;
        
        for(int i=0; i<listaSensores.size(); i++){
            for(int j=0; j<listaCasas.size() && insertado==false; j++){
               
                insertado=listaCasas.get(j).addSensor(listaSensores.get(i));

                if(Thread.interrupted()){
                    if(i<(listaSensores.size()*0.8)){
                        throw new InterruptedException();
                    }
                }
            }
            
            if(insertado==false){
                sensores_no_instalados.add(listaSensores.get(i));
            }else{
                TimeUnit.SECONDS.sleep(listaSensores.get(i).getTiempoInstalacion());
            }
        }
        
        
        
    }
    
    private String sensoresSinInstalar(){
        String resultado="";
        
        for(int i=0; i<sensores_no_instalados.size(); i++){
            resultado=sensores_no_instalados.get(i).toString() + " ";
        }
        
        return resultado;
    }
    
    private String estadoCasa(){
        String resultado="";
        
        for(int i=0; i<listaCasas.size(); i++){
            resultado=listaCasas.get(i).toString() + " ";
        }
        
        return resultado;
    }
}