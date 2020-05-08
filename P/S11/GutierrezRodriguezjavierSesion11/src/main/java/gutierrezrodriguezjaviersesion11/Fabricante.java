/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion11;


import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import gutierrezrodriguezjaviersesion11.Constantes;
import java.util.concurrent.TimeUnit;


/**
 *
 * @author pedroj
 */
public class Fabricante implements Runnable {
    
    private final String id;
    private final Constantes.TipoSensor tipo;
    private final int num_unidades;
    
    // Elementos JMS
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;

    public Fabricante(String id, Constantes.TipoSensor tipo, int num_unidades) {
        this.id = id;
        this.tipo = tipo;
        this.num_unidades = num_unidades;
    }
    
    @Override
    public void run() {
        try{
            iniciar();
            fabricacion();
            
            System.out.println("Fabricante " + id + " ha finalizado su ejecucion");
        }
        catch(InterruptedException ex){
            System.out.println(ex.toString());
        }
        finally{
            finalizar();
        }
    }
    
    private void iniciar(){
        String Broke_URL = Constantes.BROKER_URL;
        
        connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.Broke_URL);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createTopic(Constantes.QUEUE + tipo.name());
    }
    
    private void finalizar(){
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception ex) {
            // No hacer nada
        } 
    }
    
    private void fabricacion() throws InterruptedException{
        MessageProducer productor = session.createProducer(destination);
        TextMessage mensaje;
        
        if(Thread.interrupted())
            throw new InterruptedException("Fabricante: fabricacion(): Interrupcion solicitada");
        
        for(int i=0; i<num_unidades; i++){
            Sensor sensor = new Sensor(i, tipo);
            
            TimeUnit.SECONDS.sleep(tipo.tiempoFabricacion());
            
            mensaje = session.createTextMessage(id+": "+sensor.getiD());
            
            productor.send(mensaje);
        }
        
        connection.stop();
        
        productor.close();
    }
    
}
