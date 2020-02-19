package com.uja.ssccdd.sesion3.Ejercicio6;

import java.util.concurrent.Phaser;

/**
 *
 * @author José Antonio
 */
public class MainFileSearch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        FileSearch system = new FileSearch("C:\\Windows", "log", phaser);
        FileSearch apps = new FileSearch("C:\\Program Files", "log", phaser);
        FileSearch documents = new FileSearch("C:\\Documents And Settings", "log", phaser);

        // Creates a thread to run the system FileSearch and starts it
        Thread systemThread = new Thread(system, "System");
        systemThread.start();

        // Creates a thread to run the apps FileSearch and starts it
        Thread appsThread = new Thread(apps, "Apps");
        appsThread.start();

        // Creates a thread to run the documents  FileSearch and starts it
        Thread documentsThread = new Thread(documents, "Documents");
        documentsThread.start();
        try {
            systemThread.join();
            appsThread.join();
            documentsThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Terminated: %s\n", phaser.isTerminated());

    }

}
