/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion4.Ejemplo4;

import java.util.concurrent.Callable;

/**
 *
 * @author José Antonio
 */
public class TaskValidator implements Callable<String> {

    UserValidator validador;
    String user;
    String password;

    public TaskValidator(UserValidator validador, String user, String password) {
        this.validador = validador;
        this.user = user;
        this.password = password;
    }

    @Override
    public String call() throws Exception {
        if (!validador.validate(user, password)) {
            System.out.printf("%s: The user has not been found\n", validador.getName());
            throw new Exception("Error validating user");
        }
        System.out.printf("%s: The user has been found\n", validador.getName());
        return validador.getName();

    }

}
