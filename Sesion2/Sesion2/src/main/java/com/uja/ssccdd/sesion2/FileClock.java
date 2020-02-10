/*
 * Copyright (C) 2020 José Antonio
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.uja.ssccdd.sesion2;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author José Antonio
 */
public class FileClock implements Runnable {

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.printf("%s\n", new Date());
                TimeUnit.SECONDS.sleep(1);
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }
            }
        } catch (InterruptedException e) {
            System.out.printf("The FileClock has been interrupted\n");
        }
    }
}
