/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion12.Ej3;

import Constantes.GsonUtil;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author José Antonio
 */
public class MensajeListener<T> implements MessageListener {

    private final String consumerName;
    private final Class<T> typeParameterClass;
    private final GsonUtil<T> gsonUtil;

    public MensajeListener(String consumerName, Class<T> typeParameterClass) {
        this.consumerName = consumerName;
        this.typeParameterClass = typeParameterClass;
        this.gsonUtil = new GsonUtil();
    }

    /**
     * Interfaz para obtener el texto
     *
     * @param message
     */
    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                T obj = gsonUtil.decode(textMessage.getText(), typeParameterClass);
                System.out.println(consumerName + " processing job: " + obj);
            } else {
                System.out.println(consumerName + " Unknown message");
            }
        } catch (JMSException e) {
        }
    }
}
