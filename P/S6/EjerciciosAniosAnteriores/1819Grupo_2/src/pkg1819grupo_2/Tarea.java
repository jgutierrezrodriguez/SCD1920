/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo_2;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import static pkg1819grupo_2.Main.CASO_BASE;
import static pkg1819grupo_2.Main.DIVISION_TAREAS;
import static pkg1819grupo_2.Main.ElementoResultado.APARICIONES_MAYOR;
import static pkg1819grupo_2.Main.ElementoResultado.APARICIONES_MENOR;
import static pkg1819grupo_2.Main.ElementoResultado.MAYOR;
import static pkg1819grupo_2.Main.ElementoResultado.MENOR;
import static pkg1819grupo_2.Main.INICIALIZACION;
import static pkg1819grupo_2.Main.PAR;

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

    // Constantes
    private static int PRIMERO = 0;

    public Tarea(int[][] datos, int inicioFila, int inicioColumna, int numDatos) {
        this.datos = datos;
        this.inicioFila = inicioFila;
        this.inicioColumna = inicioColumna;
        this.numDatos = numDatos;
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

            Tarea tarea = new Tarea(datos, inicioI, inicioJ, mitad);

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
                Resultado resultadoParcial = listaTareas.get(i).get();
                
                if ( resultadoParcial.getResultado(MAYOR) > resultado.getResultado(MAYOR) ) {
                    resultado.setResultado(MAYOR, resultadoParcial.getResultado(MAYOR));
                    resultado.setResultado(APARICIONES_MAYOR, resultadoParcial.getResultado(APARICIONES_MAYOR));
                } else if ( resultadoParcial.getResultado(MAYOR) == resultado.getResultado(MAYOR) ) {
                    resultado.incrementar(APARICIONES_MAYOR, resultadoParcial.getResultado(APARICIONES_MAYOR));
                } 
                
                if ( resultadoParcial.getResultado(MENOR) < resultado.getResultado(MENOR) ) {
                    resultado.setResultado(MENOR, resultadoParcial.getResultado(MENOR));
                    resultado.setResultado(APARICIONES_MENOR, resultadoParcial.getResultado(APARICIONES_MENOR));
                } else if ( resultadoParcial.getResultado(MENOR) == resultado.getResultado(MENOR) ) {
                    resultado.incrementar(APARICIONES_MENOR, resultadoParcial.getResultado(APARICIONES_MENOR));
                }
            }
        }
 
        return resultado;
    }

    private Resultado casoBase() {
        Resultado resultado = new Resultado(datos[inicioFila][inicioColumna]);

        for (int i = inicioFila; i < (inicioFila + numDatos); i++) {
            for (int j = inicioColumna; j < (inicioColumna + numDatos); j++) {
                if (datos[i][j] > resultado.getResultado(MAYOR)) {
                    resultado.setResultado(MAYOR, datos[i][j]);
                    resultado.setResultado(APARICIONES_MAYOR, INICIALIZACION);
                } else if (datos[i][j] == resultado.getResultado(MAYOR)) {
                    resultado.incrementar(APARICIONES_MAYOR);
                }
                
                if (datos[i][j] < resultado.getResultado(MENOR)) {
                    resultado.setResultado(MENOR, datos[i][j]);
                    resultado.setResultado(APARICIONES_MENOR, INICIALIZACION);
                } else if (datos[i][j] == resultado.getResultado(MENOR)) {
                    resultado.incrementar(APARICIONES_MENOR);
                }
            }
        }

        return resultado;
    }
}
