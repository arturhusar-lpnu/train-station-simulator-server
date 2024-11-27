package com.simulation.generators;

import lombok.Getter;
import lombok.Setter;
import com.simulation.models.Client;
import com.simulation.models.PayDeck;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@Getter
@Setter
public class ClientMoveSystem {
    private ExecutorService threadPool;
    private List<List<Lock>> matrixLock;

    public ClientMoveSystem() {
        this.threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            List<Lock> row = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                row.add(new ReentrantLock());
            }
            this.matrixLock.add(row);
        }
    }

    public ClientMoveSystem(List<List<Lock>> matrixLock) {
        this.threadPool = Executors.newCachedThreadPool();
        this.matrixLock = matrixLock;
    }

    public void addClient(List<PayDeck> paydecks, Client client) {

    }
}
