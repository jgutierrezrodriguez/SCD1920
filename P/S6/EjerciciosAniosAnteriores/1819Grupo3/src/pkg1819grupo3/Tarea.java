/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo3;

import static pkg1819grupo3.Main.CASO_BASE;
import static pkg1819grupo3.Main.DIVISION_TAREAS;
import static pkg1819grupo3.Main.PAR;
import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author pedroj 
 */
public class Tarea extends RecursiveAction {
    // Versión UID porque implementa la interface Serializable
    private static final long serialVersionUID = 1L; 
    
    private final int[][] datos;
    private final int inicioFila;
    private final int inicioColumna;
    private final int numDatos;
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
        // Comprobamos si podemos resolver nuestra tarea y si no la dividimos
        if ( (numDatos > CASO_BASE) && (numDatos % 2 == PAR) ) {
            // Dividimos la tarea
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
        } else {
            // Resolvemos el caso base
            casoBase();
        }
    }
    
    private void casoBase() {
        for ( int i = inicioFila; i < (inicioFila + numDatos); i++ )
            for ( int j = inicioColumna; j < (inicioColumna + numDatos); j++ ) {
                    exm.lock();
                    try {
                        resultado.incSum(datos[i][j]);
                    } finally {
                        exm.unlock();
                    }
            }
  
    }
}