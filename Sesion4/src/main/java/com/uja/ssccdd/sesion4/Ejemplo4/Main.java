/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion4.Ejemplo4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author José Antonio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String user1 = "Hola", user2 = "Adios", pass1 = "Pass1", pass2 = "Pass2";
        UserValidator validador1 = new UserValidator("Validador1");
        UserValidator validador2 = new UserValidator("Validador2");
        TaskValidator tarea1 = new TaskValidator(validador1, user1, pass1);
        TaskValidator tarea2 = new TaskValidator(validador2, user2, pass2);
        List<TaskValidator> lista = new ArrayList<>();
        lista.add(tarea1);
        lista.add(tarea2);
        ExecutorService executor = (ExecutorService) Executors.newCachedThreadPool();
        String result;
        try {
            // Send the list of tasks to the executor and waits for the result of the first task 
            // that finish without throw and Exception. If all the tasks throw and Exception, the
            // method throws and ExecutionException.
            result = executor.invokeAny(lista);
            System.out.printf("Main: Result: %s\n", result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

// Shutdown the Executor
        executor.shutdown();
        System.out.printf("Main: End of the Execution\n");

    }

}
