/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion3.Ejercicio3;

/**
 *
 * @author José Antonio
 */
public class MainProducerConsumer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /**
     * Creates a simulated file with 100 lines
     */
    FileMock mock=new FileMock(101, 10);
		
    /**
     * Creates a buffer with a maximum of 20 lines
     */
    Buffer buffer=new Buffer(20);
		
    /**
     * Creates a producer and a thread to run it
     */
    Producer producer=new Producer(buffer, mock);
    Thread threadProducer=new Thread(producer,"Producer");
		
    /**
     * Creates three consumers and threads to run them
     */
    Consumer consumers[]=new Consumer[3];
    Thread threadConsumers[]=new Thread[3];
		
    for (int i=0; i<3; i++){
        consumers[i]=new Consumer(buffer);
        threadConsumers[i]=new Thread(consumers[i],"Consumer "+i);
    }
		
    /**
     * Strats the producer and the consumers
     */
    threadProducer.start();
    for (int i=0; i<3; i++){
        threadConsumers[i].start();
    }

    }
    
}
