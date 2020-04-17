/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo1;

import java.util.Date;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jgr97
 */
public class Peticion implements Constantes, Comparable<Peticion>, Runnable{

    private final Prioridad prioridad;
    private final String idCliente;
    private final ConcurrentLinkedDeque<Pedido> listaPedidos;
    private final Date horaincial;

    public Peticion(Prioridad prioridad, String idCliente, ConcurrentLinkedDeque<Pedido> listaPedidos, Date horaincial) {
        this.prioridad = prioridad;
        this.idCliente = idCliente;
        this.listaPedidos = listaPedidos;
        this.horaincial = horaincial;
    }

    Peticion(String idCliente) {
        this.prioridad = null;
        this.idCliente = idCliente;
        this.listaPedidos = null;
        this.horaincial = null;
    }

    public Date getHoraincial() {
        return horaincial;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public ConcurrentLinkedDeque<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    @Override
    public void run() {
        try{
            ProcesarPeticion();
        }catch(InterruptedException ex){
            System.out.println(this + "ha sido interrumpido " + ex);
        }
    }
    
    void ProcesarPeticion () throws InterruptedException{
        int tiempoServicio;
        
        if (Thread.interrupted()){
            throw new InterruptedException();
        }
        
        tiempoServicio = TIEMPO_MINIMO + generador.nextInt(VARIACION_TIEMPO);
        tiempoServicio = tiempoServicio * prioridad.getPenalizacion();
        TimeUnit.SECONDS.sleep(tiempoServicio);
        
        Date finpeticion = new Date();
        Pedido ped = new Pedido(idCliente+"-"+prioridad, finpeticion, finpeticion);
        
        listaPedidos.add(ped);
    }

    @Override
    public int compareTo(Peticion o) {
        Prioridad prio = o.getPrioridad();
        
        return this.prioridad.compareTo(prio);
    }

    @Override
    public boolean equals(Object obj) {
        boolean resultado = false;
        
        if(obj instanceof Peticion){
            resultado= this.idCliente.equals(((Peticion)obj).getIdCliente());
        }
        return resultado;
    }

    @Override
    public String toString() {
        String resultado = "PETICION(" + idCliente + "," + prioridad + ")";
        
        return resultado;
    }
 
}
