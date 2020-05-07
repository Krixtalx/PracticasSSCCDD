
package com.uja.ssccdd.sesion12.Ej2;

import static Constantes.Constantes.*;
import Constantes.GsonUtil;
import Constantes.Resource;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

public class SyncSubscriber implements Runnable {
    private final String iD;
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;

    public SyncSubscriber(String iD) {
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
        } catch (Exception ex) {
            // No hacer nada
        }    
    }

    public void execution() throws Exception {
        MessageConsumer consumer = session.createConsumer(destination);
        // Creamos la utilizad para la recepción del objeto Java
        GsonUtil<Resource> gsonUtil = new GsonUtil();
        
        for (int i = 0; i < (NUM_MSG * PUBLISHER); ++i) {
            TextMessage msg = (TextMessage) consumer.receive();
            Resource resource = gsonUtil.decode(msg.getText(), Resource.class);
            System.out.println(iD + " processing job: " + resource);
        }
        
        connection.stop();
        consumer.close();
    }

    public String getiD() {
        return iD;
    }
}

