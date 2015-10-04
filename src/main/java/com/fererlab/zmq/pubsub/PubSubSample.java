package com.fererlab.zmq.pubsub;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PubSubSample implements Runnable{

    private ExecutorService executorService;
    private PubServer pubServer;
    private SubClient subClient;

    public PubSubSample() {
        executorService = Executors.newFixedThreadPool(2);
        pubServer = new PubServer();
        subClient = new SubClient();
    }

    @Override
    public void run() {
        executorService.execute(pubServer);
        executorService.execute(subClient);
    }

}
