/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion11;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author pedroj
 */
public class Promotor implements Runnable {
    
    private final String id;
    private final int num_casas;
    private final ArrayList<Casa> casas_instaladas;
    
    // Elementos JMS
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private ArrayList<Destination> destinations;

    public Promotor(String id, int num_casas) {
        this.id = id;
        this.num_casas = num_casas;
        this.casas_instaladas = new ArrayList<>();
    }

    @Override
    public void run() {
        try{
            iniciar();
            instalacion();
            
            System.out.println("Promotor " + id + " ha finalizado su ejecucion");
        }catch(InterruptedException ex){
            System.out.println(ex.toString());
        }finally{
            finalizar();
        }
    }
    
    private void iniciar(){
        connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        for(Constantes.TipoSensor c : Constantes.TipoSensor.values()){
            Destination destino = session.createTopic(Constantes.QUEUE + c);
            destinations.add(destino);
        }
    }
    
    public void finalizar() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception ex) {
            // No hacer nada
        }    
    }
    
    private void instalacion() throws InterruptedException{
        int casas_instaladas = 0;
        
        ArrayList<MessageConsumer> consumidores = new ArrayList();
        
        for(int i=0; i<destinations.size(); i++){
            Destination dest = destinations.get(i);
            MessageConsumer consum = session.createConsumer(dest);
            consumidores.add(consum);
        }
        
        
        while(casas_instaladas<num_casas){
            
            Constantes.TipoCasa tipo = Constantes.TipoCasa.getCasa();
            Casa casa =  new Casa(casas_instaladas, tipo);
            
            for(int i=0; i<casa.getNumHabitaciones(); i++){
                for(int j=0; j< Constantes.TipoSensor.values().length; j++){
                    TextMessage mensaje = (TextMessage) consumidores.get(j).recieve();
                    
                    Constantes.TipoSensor sesnortipo = Constantes.TipoSensor.values()[j];
                    Sensor sensor = new Sensor(i, sesnortipo);
                    
                    TimeUnit.SECONDS.sleep(sesnortipo.tiempoInstalacion());
                }
            }
            
            casas_instaladas++;
            
            this.casas_instaladas.add(casa);
        }
        
        connection.stop();
        
        for(int i=0; i<consumidores.size(); i++){
            consumidores.get(i).close();
        }
    }
    
}
