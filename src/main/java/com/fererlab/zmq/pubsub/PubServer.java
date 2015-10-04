package com.fererlab.zmq.pubsub;

import org.zeromq.ZMQ;

import java.util.Random;
import java.util.logging.Logger;

public class PubServer implements Runnable {

    private final Logger L = Logger.getLogger(this.getClass().getSimpleName());

    @Override
    public void run() {

        // sleep 2 seconds, let the clients start first
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // simply ignore this exception
        }

        // start the zmq context
        ZMQ.Context context = ZMQ.context(1);
        L.info("context created");

        // create socket
        ZMQ.Socket publisher = context.socket(ZMQ.PUB);
        L.info("socket created");

        // bind to socket
        publisher.bind("tcp://*:5556");
        L.info("bound to socket");

        while (!Thread.currentThread().isInterrupted()) {
            // publish some message
            // publisher.send("current number: " + (new Random().nextInt()));
            publisher.send(String.valueOf(new Random().nextInt(10)));
            try {
                // just don't flood
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // safely ignore this exception
            }
        }

        // close the socket
        publisher.close();
        L.info("closed socket");

        // terminate the context
        context.term();
        L.info("closed context");

    }
}
