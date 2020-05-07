package com.uja.ssccdd.sesion12.Ej3;

import static Constantes.Constantes.*;
import Constantes.Resource;
import java.util.concurrent.TimeUnit;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author pedroj
 */
public class AsyncSubscriber implements Runnable {
    private final String iD;
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;

    public AsyncSubscriber(String iD) {
        this.iD = iD;
    }

    @Override
    public void run() {
        System.out.println("HILO-" + getiD() +" Starting example SyncConsumer now...");
        
        try {
            before();
            execution();
        } catch (Exception e) {
            System.out.println("HILO-" + getiD() + 
                               " Caught an exception during the example: " + e.getMessage());
        } finally {
            after();
            System.out.println("HILO-" + getiD() + " Finished running the sample SyncConsumer");
        }
    }
    
    public void before() throws Exception {
        connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createTopic(TOPIC);
    }

    public void after() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (JMSException ex) {
            // No hacer nada
        }    
    }

    public void execution() throws Exception {
        MessageConsumer consumer = session.createConsumer(destination);
        
        consumer.setMessageListener(new MensajeListener<>(iD, Resource.class));
        
        // Espera antes de finalizar
        TimeUnit.MINUTES.sleep(TIME_SLEEP);
        
        connection.stop();
        consumer.close();
    }

    public String getiD() {
        return iD;
    }
}