/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion4.Ejemplo4;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author José Antonio
 */
public class UserValidator {

    private final String name;

    public UserValidator(String name) {
        this.name = name;
    }

    /**
     * Method that validates a user
     *
     * @param name Name of the user
     * @param password Password of the user
     * @return true if the user is validated and false if not
     */
    public boolean validate(String name, String password) {
        // Create a new Random objects generator
        Random random = new Random();

        // Sleep the thread during a random period of time
        try {
            Long duration = (long) (Math.random() * 10);
            System.out.printf("Validator %s: Validating a user during %d seconds\n", this.name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            return false;
        }

        // Return a random boolean value
        return random.nextBoolean();
    }

    /**
     * Return the name of the validation system
     *
     * @return The name of the validation system
     */
    public String getName() {
        return name;
    }

}
