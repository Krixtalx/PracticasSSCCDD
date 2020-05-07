package guionsesion11;

import static guionsesion11.Constantes.BROKER_URL;
import static guionsesion11.Constantes.QUEUE;
import static guionsesion11.Constantes.TIME_SLEEP;
import java.util.concurrent.TimeUnit;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author pedroj
 */
public class AsyncConsumer implements Runnable {

    private final String iD;
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;

    public AsyncConsumer(String iD) {
        this.iD = iD;
    }

    public void before() throws Exception {
        connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue(QUEUE);
    }

    @Override
    public void run() {
        System.out.println("HILO-" + getiD() + " Starting example AsyncConsumer now...");

        try {
            before();
            execution();
        } catch (Exception e) {
            System.out.println("HILO-" + getiD()
                    + " Caught an exception during the example: " + e.getMessage());
        } finally {
            after();
            System.out.println("HILO-" + getiD() + " Finished running the sample AsyncConsumer");
        }
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

        consumer.setMessageListener(new TextMsgListener("AsycConsumer"));

        // Espera antes de finalizar
        TimeUnit.MINUTES.sleep(TIME_SLEEP);

        connection.stop();
        consumer.close();
    }

    public String getiD() {
        return iD;
    }
}
