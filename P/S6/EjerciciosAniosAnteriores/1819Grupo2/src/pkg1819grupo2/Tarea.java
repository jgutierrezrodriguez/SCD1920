/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo2;

import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.locks.ReentrantLock;
import static pkg1819grupo2.Main.DIVISION_TAREAS;

/**
 *
 * @author jgr97
 */
public class Tarea extends RecursiveAction{

    private static final long serialVersionUID = 1L;
    
    private int[][] datos;
    private int inicioFila;
    private int inicioColumna;
    private int numDatos;
    private final ReentrantLock exm;
    private final Resultado resultado;

    public Tarea(int[][] datos, int inicioFila, int inicioColumna, int numDatos, ReentrantLock exm, Resultado resultado) {
        this.datos = datos;
        this.inicioFila = inicioFila;
        this.inicioColumna = inicioColumna;
        this.numDatos = numDatos;
        this.exm = exm;
        this.resultado = resultado;
    }
    
    
    
    
    
    @Override
    protected void compute() {
        if((numDatos < Main.CASO_BASE) &&  (numDatos%2 == Main.PAR)){
            System.out.println("Hilo(TAREA) tareas pendientes de finalización: " + getQueuedTaskCount());
            int mitad = numDatos / 2;
            ArrayList<Tarea> listaTareas = new ArrayList();
            //Resultado[] listaResultados = new 
            for ( int i = 0; i < DIVISION_TAREAS; i++ ) {
                int inicioI = this.inicioFila + (mitad * (i / 2));
                int inicioJ = this.inicioColumna + (mitad * (i % 2));
                
                Tarea tarea = new Tarea(datos,inicioI,inicioJ,mitad,exm,resultado);
                
                listaTareas.add(tarea);
            }
            invokeAll(listaTareas);
        }
        else{
            CasoBase();
        }
    }
    
    private void CasoBase(){
        for(int i=inicioFila; i < (inicioFila+numDatos); i++){
            for(int j=inicioColumna; j < (inicioColumna+numDatos); j++){
                if(datos[i][j] % 2 == Main.PAR){
                    exm.lock();
                    
                    try{
                        resultado.incrementar(Main.PAR);
                    }finally{
                        exm.unlock();
                    }
                }
                
                else{
                    exm.lock();
                    
                    try{
                        resultado.incrementar(Main.IMPAR);
                    }finally{
                        exm.unlock();
                    }
                }
            }
        }
    }
}
