package com.simulation.models;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.locks.Lock;

@Getter
@Setter
public class ClientMoveTask implements Runnable {
    private Client client;
    private Lock lock;

    public ClientMoveTask(Client client, Lock lock) {
        this.client = client;
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.lock();
        try {

        } finally {
            lock.unlock();
        }
    }
}