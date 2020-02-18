/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion3;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author José Antonio
 */
public class Buffer {

    private final LinkedList<String> buffer;
    private final int maxSize;
    private final ReentrantLock lock;
    private final Condition lines;
    private final Condition space;
    private boolean lineasPendientes;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        buffer = new LinkedList<>();
        lock = new ReentrantLock();
        lines = lock.newCondition();
        space = lock.newCondition();
        lineasPendientes = true;
    }

    public void insert(String line) throws InterruptedException {
        lock.lock();
        try {
            while (buffer.size() >= maxSize) {
                space.await();
            }
            buffer.offer(line);
            System.out.println(Thread.currentThread().getName() + " ha insertado en la pos " + buffer.size());
            lines.signalAll();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String get() {
        String linea = null;
        lock.lock();
        try {
            while (buffer.size() <= 0 && isLineasPendientes()) {
                lines.await();
            }
            if (isLineasPendientes()) {
                linea=buffer.poll();
                System.out.println(Thread.currentThread().getName() + " ha leido en la pos " + buffer.size());
                space.signalAll();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
        return linea;
    }

    /**
     * @return the lineasPendientes
     */
    public boolean isLineasPendientes() {
        return lineasPendientes;
    }

    /**
     * @param lineasPendientes the lineasPendientes to set
     */
    public void setLineasPendientes(boolean lineasPendientes) {
        this.lineasPendientes = lineasPendientes;
    }

}
