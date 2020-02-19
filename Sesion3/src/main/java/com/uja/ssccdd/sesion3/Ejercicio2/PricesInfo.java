package com.uja.ssccdd.sesion3.Ejercicio2;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author José Antonio
 */
public class PricesInfo {

    private double price1;
    private double price2;

    /**
     * Lock to control the access to the prices
     */
    private final ReadWriteLock lock;

    /**
     * Constructor of the class. Initializes the prices and the Lock
     */
    public PricesInfo() {
        price1 = 1.0;
        price2 = 2.0;
        lock = new ReentrantReadWriteLock();
    }

    public double getPrice1() {
        lock.readLock().lock();
        double value;
        try {
            value = price1;
        } finally {
            lock.readLock().unlock();
        }
        return value;
    }

    public double getPrice2() {
        lock.readLock().lock();
        double value;
        try {
            value = price2;
        } finally {
            lock.readLock().unlock();
        }
        return value;
    }

    public void setPrices(double price1, double price2) {
        lock.writeLock().lock();
        try {
            this.price1 = price1;
            this.price2 = price2;
        } finally {
            lock.writeLock().unlock();
        }
    }
}
