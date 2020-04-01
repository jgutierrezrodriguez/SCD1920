/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo4;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import static pkg1819grupo4.Main.CASO_BASE;
import static pkg1819grupo4.Main.DIVISION_TAREAS;
import static pkg1819grupo4.Main.PAR;

/**
 *
 * @author jgr97
 */
public class Tarea extends RecursiveTask<Resultado> {

    // Versión UID porque implementa la interface Serializable
    private static final long serialVersionUID = 1L;

    private final int[][] datos;
    private final int inicioFila;
    private final int inicioColumna;
    private final int numDatos;
    private final int valorBuscado;

    // Constantes
    private static int PRIMERO = 0;

    public Tarea(int[][] datos, int inicioFila, int inicioColumna, int numDatos, int valorBuscado) {
        this.datos = datos;
        this.inicioFila = inicioFila;
        this.inicioColumna = inicioColumna;
        this.numDatos = numDatos;
        this.valorBuscado = valorBuscado;
    }

    @Override
    protected Resultado compute() {
        Resultado resultado = null;
        ArrayList<Tarea> listaTareas = new ArrayList();

        // Comprobamos si podemos resolver nuestra tarea y si no la dividimos
        if ( (numDatos > CASO_BASE) && (numDatos % 2 == PAR) ) {
            // Dividimos la tarea
            dividirTarea(listaTareas);
            try {
                resultado = calcularResultado(listaTareas);
            } catch (InterruptedException | ExecutionException ex) {
                System.out.println("Hilo(TAREA) " + ex);
            }
        } else {
            resultado = casoBase();
        }

        return resultado;
    }

    private void dividirTarea(ArrayList<Tarea> listaTareas) {
        System.out.println("Hilo(TAREA) tareas pendientes de finalización: " + getQueuedTaskCount());
        int mitad = numDatos / 2;
        for (int i = 0; i < DIVISION_TAREAS; i++) {
            int inicioI = this.inicioFila + (mitad * (i / 2));
            int inicioJ = this.inicioColumna + (mitad * (i % 2));

            Tarea tarea = new Tarea(datos, inicioI, inicioJ, mitad, valorBuscado);

            listaTareas.add(tarea);
        }
        invokeAll(listaTareas);
    }

    private Resultado calcularResultado(ArrayList<Tarea> listaTareas) throws InterruptedException, ExecutionException {
        Resultado resultado=null;

        for (int i = 0; i < DIVISION_TAREAS; i++ ) {
            if ( i == PRIMERO ) {
                resultado = listaTareas.get(i).get();
            } else {
                resultado.incrementar(listaTareas.get(i).get().getAparicion());
            }
        }
 
        return resultado;
    }

    private Resultado casoBase() {
        Resultado resultado = new Resultado(valorBuscado);

        for (int i = inicioFila; i < (inicioFila + numDatos); i++) 
            for (int j = inicioColumna; j < (inicioColumna + numDatos); j++) 
                if ( datos[i][j] == valorBuscado ) 
                        resultado.incrementar();

        return resultado;
    }
}