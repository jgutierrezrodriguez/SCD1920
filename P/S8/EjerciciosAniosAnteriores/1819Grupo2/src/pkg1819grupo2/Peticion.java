/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo2;

import java.util.Date;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author pedroj
 */
public class Peticion implements Constantes, Delayed, Runnable {
    private final Prioridad prioridad;
    private final String idCliente;
    private final ConcurrentLinkedDeque<Pedido> listaPedidos;
    private final Date inicioPeticion;
    private final Date retardoPeticion;

    public Peticion(Prioridad prioridad, String idCliente, ConcurrentLinkedDeque<Pedido> listaPedidos, 
                                            Date inicioPeticion, Date retardo) {
        this.prioridad = prioridad;
        this.idCliente = idCliente;
        this.listaPedidos = listaPedidos;
        this.inicioPeticion = inicioPeticion;
        this.retardoPeticion = retardo;
    }
    
    /**
     * Constructor exclusivo para la búsqueda de ocurrencias de peticiones
     * @param idCliente 
     */
    public Peticion(String idCliente) {
        this.prioridad = null;
        this.idCliente = idCliente;
        this.listaPedidos = null;
        this.inicioPeticion = null;
        this.retardoPeticion = null;
    }
    
    @Override
    public void run() {
        try {
            procesarPeticion();
        } catch (InterruptedException ex) {
            System.out.println(this + " ha sido interrumpida " + ex);
        }
    }
    
    @Override
    public long getDelay(TimeUnit unit) {
        // Tiempo que resta para la activación de la tarea
        Date actual = new Date();
        long diferencia = retardoPeticion.getTime() - actual.getTime();
        return unit.convert(diferencia, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        int resultado;
        
        // Comparamos los tiempos de retardoPeticion de dos tareas
        long diferencia = this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
        
        if ( diferencia < 0 )
            resultado = MENOR;
        else if ( diferencia > 0 )
            resultado = MAYOR;
        else 
            resultado = IGUAL;
        
        return resultado;
    }
    
    private void procesarPeticion() throws InterruptedException {
        int tiempoServicio;
        
        // Comprobamos que no hay que finalizar
        if (Thread.interrupted())
            throw new InterruptedException();
        
        // Simulamos el tiempo de servicio
        tiempoServicio = TIEMPO_MINIMO + generador.nextInt(VARIACION_TIEMPO);
        TimeUnit.SECONDS.sleep(tiempoServicio);
        
        Date finPeticion = new Date();
        Pedido pedido = new Pedido(idCliente+"-" +prioridad, inicioPeticion, finPeticion);
        listaPedidos.add(pedido);
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean resultado = false;
        
        if (obj instanceof Peticion)
            resultado = this.idCliente.equals(((Peticion)obj).getIdCliente());
        
        return resultado;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public String getIdCliente() {
        return idCliente;
    }

    @Override
    public String toString() {
        return "PETICION(" + idCliente + "," + prioridad + ")";
    }  
}