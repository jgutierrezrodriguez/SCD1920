/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo2;

import java.util.Random;

/**
 *
 * @author jgr97
 */
public class Main {
    
    public final static Random generador = new Random();
    
    // Constantes
    public static enum ComponenteOrdenador {CPU, MEMORIA, PERIFERICO};
    public static final ComponenteOrdenador[] TIPOS_COMPONENTES = ComponenteOrdenador.values();
    public static final int NUM_TAREAS_COMPONENTES = 3;
    public static final int NUM_TAREAS_ORDENADOR = 8;
    public static final int PEDIDO_ORDENADORES = 10;
    public static final int NUM_TAREAS_FINALIZADAS = 1;
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
