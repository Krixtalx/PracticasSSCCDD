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

import java.io.File;

/**
 *
 * @author José Antonio
 */
public class FileSearch implements Runnable {

    private String initPath;
    private String fileName;

    public FileSearch(String initPath, String fileName) {
        this.initPath = initPath;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        File archivo = new File(initPath);
        if (archivo.isDirectory()) {
            try {
                directoryProcess(archivo);
            } catch (InterruptedException ex) {
                System.out.println("La busqueda se ha detenido: " + Thread.currentThread().getName());
                //cleanResources();
            }
        }
    }

    private void directoryProcess(File archivo) throws InterruptedException {
        File files[] = archivo.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    directoryProcess(file);
                } else {
                    fileProcess(file);
                }
            }
        }
        if(Thread.interrupted())
            throw new InterruptedException();
    }

    private void fileProcess(File archivo) throws InterruptedException{
        if(archivo.getName().equals(fileName)){
            System.out.println(Thread.currentThread().getName()+" : "+archivo.getAbsolutePath());
        }
        if(Thread.interrupted())
            throw new InterruptedException();
    }
}
