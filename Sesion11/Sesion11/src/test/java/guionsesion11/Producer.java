package guionsesion11;

import static guionsesion11.Constantes.BROKER_URL;
import static guionsesion11.Constantes.NUM_MSG;
import static guionsesion11.Constantes.QUEUE;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;


public class Producer implements Runnable {
    private final String iD;
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;

    public Producer(String iD) {
        this.iD = iD;
    }
    
    @Override
    public void run() {
        System.out.println("HILO-" + getiD() + " Starting example Q Producer now...");
        
        try {
            before();
            execution();
        } catch (Exception e) {
            System.out.println("HILO-" + getiD() + 
                               " Caught an exception during the example: " + e.getMessage());
        } finally {
            after();
            System.out.println("HILO-" + getiD() + " Finished running the sample Q Producer");
        }
    }
    
    public void before() throws Exception {
        connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue(QUEUE);
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
        MessageProducer producer = session.createProducer(destination);

        for (int i = 0; i < NUM_MSG; ++i) {
            TextMessage message = session.createTextMessage("Job number: " + i);
            producer.send(message);
            System.out.println("Producer sent Job("+i+")");
        }

        producer.close();
    }

    public String getiD() {
        return iD;
    }    
}
