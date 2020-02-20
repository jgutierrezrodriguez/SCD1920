/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repasop1;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jgr97
 */
public class RepasoP1 {

    public static final int MAX_TRABAJOS_REALIZADOS = 10;
    public static final int NUM_IMPRESORAS = 3;
    public static final int MIN_TIEMPO_TRABAJO = 2;
    public static final int MAX_TIEMPO_TRABAJO = 3;
    public static final float PROB_REALIZAR_TRABAJO = 0.3f;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Impresora[] impresoras;
        ArrayList<TrabajoImpresion> trabajos;
        GestorImpresora gestor;
        Random generador;
        
        System.out.println("Ha iniciado la ejecución el Hilo(PRINCIPAL)");
        
        // Inicialización de las variables
        impresoras = new Impresora[NUM_IMPRESORAS];
        for (int i = 0; i < NUM_IMPRESORAS; i++) {
            Impresora nuevaImpresora = new Impresora(i);
            impresoras[i] = nuevaImpresora;
        }
        
        trabajos = new ArrayList();
        gestor = new GestorImpresion(impresoras, trabajos);
        
        generador = new Random();
        
        // Trabajo con el gestor hasta completar el número de trabajos previsto
        int completados = 0;
        int idTrabajo = 0;
        while ( completados < MAX_TRABAJOS_REALIZADOS ) {
            if ( (generador.nextFloat() < PROB_REALIZAR_TRABAJO) && gestor.hayTrabajos() ) {
                // Se completa un trabajo de impresión
                completados++;
                try {
                    // Tratamos la interrupción por defecto
                    gestor.realizarTrabajo();
                    System.out.println("Hilo(Principal) se han completado " + completados +
                            " trabajos");
                } catch (InterruptedException ex) {
                    Logger.getLogger(Sesion1.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // Agregamos un nuevo trabajo al gestor que queda pendiente para
                // atenderlo más adelante
                idTrabajo++;
                int tiempo = MIN_TIEMPO_TRABAJO + generador.nextInt(MAX_TIEMPO_TRABAJO);
                TrabajoImpresion nuevoTrabajo = new TrabajoImpresion(idTrabajo, tiempo);
                gestor.agregarTrabajo(nuevoTrabajo);
            }
        }
        
        System.out.println("Ha finalizado la ejecución el Hilo(PRINCIPAL)");
    }

    
    
}

