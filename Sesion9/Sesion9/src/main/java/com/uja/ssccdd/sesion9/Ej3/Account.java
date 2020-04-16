/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssccdd.sesion9.Ej3;

import java.util.concurrent.atomic.AtomicLong;

/**
 * This class simulate a bank account
 *
 */
public class Account {

    /**
     * Balance of the bank account
     */
    private AtomicLong balance;

    public Account() {
        balance = new AtomicLong();
    }

    /**
     * Returns the balance of the account
     *
     * @return the balance of the account
     */
    public long getBalance() {
        return balance.get();
    }

    /**
     * Establish the balance of the account
     *
     * @param balance the new balance of the account
     */
    public void setBalance(long balance) {
        this.balance.set(balance);
    }

    /**
     * Add an import to the balance of the account
     *
     * @param amount import to add to the balance
     */
    public void addAmount(long amount) {
        this.balance.getAndAdd(amount);
    }

    /**
     * Subtract an import to the balance of the account
     *
     * @param amount import to subtract to the balance
     */
    public void subtractAmount(long amount) {
        this.balance.getAndAdd(-amount);
    }

}
