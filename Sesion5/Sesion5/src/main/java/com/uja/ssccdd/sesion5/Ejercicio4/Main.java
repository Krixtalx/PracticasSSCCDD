/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion5.Ejercicio4;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author José Antonio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService ejecutor = new ScheduledThreadPoolExecutor(6);
        ResultTask resultTasks[] = new ResultTask[5];
        for (int i = 0; i < 5; i++) {
            ExecutableTask executableTask = new ExecutableTask("Task " + i);
            resultTasks[i] = new ResultTask(executableTask);
            ejecutor.submit(resultTasks[i]);
        }

        TimeUnit.SECONDS.sleep(5);
        for (int i = 0; i < 5; i++) {
            resultTasks[i].cancel(true);
        }
        
        for (ResultTask resultTask : resultTasks) {
            try {
                if (!resultTask.isCancelled()) {
                    System.out.printf("%s\n", resultTask.get());
                }
            }catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        ejecutor.shutdown();

    }

}
