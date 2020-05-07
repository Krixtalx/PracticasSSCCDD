package guionsesion11;

import static guionsesion11.Constantes.BROKER_URL;
import static guionsesion11.Constantes.NUM_MSG;
import static guionsesion11.Constantes.QUEUE;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author pedroj
 */
public class SyncConsumer implements Runnable {

    private final String iD;
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;

    public SyncConsumer(String iD) {
        this.iD = iD;
    }

    @Override
    public void run() {
        System.out.println("HILO-" + getiD() + " Starting example SyncConsumer now...");

        try {
            before();
            execution();
        } catch (Exception e) {
            System.out.println("HILO-" + getiD()
                    + " Caught an exception during the example: " + e.getMessage());
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
        MessageConsumer consumer = session.createConsumer(destination);

        for (int i = 0; i < NUM_MSG; ++i) {
            TextMessage msg = (TextMessage) consumer.receive();
            System.out.println("Worker processing job: " + msg.getText());
        }

        connection.stop();
        consumer.close();
    }

    public String getiD() {
        return iD;
    }
}
