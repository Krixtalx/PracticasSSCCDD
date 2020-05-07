package guionsesion11;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Ejemplo de procesamiento basico de una interfaz listener para recibir
 * mensajes en JMS
 */
public class TextMsgListener implements MessageListener {

    private String consumerName;

    public TextMsgListener(String consumerName) {
        this.consumerName = consumerName;
    }

    /**
     * Interfaz para obtener el texto
     */
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                System.out.println(consumerName + " received " + textMessage.getText());
            } else {
                System.out.println("Unknown message");
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
