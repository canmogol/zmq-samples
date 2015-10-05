package com.fererlab.zmq.parallelpipeline;

import org.zeromq.ZMQ;

import java.util.logging.Logger;

public class Sink implements Runnable {

    private final Logger L = Logger.getLogger(this.getClass().getSimpleName());

    @Override
    public void run() {

        // create zmq context
        ZMQ.Context context = ZMQ.context(1);
        L.info("context created");

        // create socket
        ZMQ.Socket receiver = context.socket(ZMQ.PULL);
        L.info("receiver PULL socket created");

        // connect to socket
        receiver.bind("tcp://localhost:5559");
        L.info("receiver PULL socket connected");

        while (!Thread.currentThread().isInterrupted()) {
            L.info("will receive next message");
            // receive message
            String message = new String(receiver.recv(0)).trim();
            L.info("received message: " + message);
        }

        // close socket
        receiver.close();
        L.info("socket closed");

        // terminate context
        context.term();
        L.info("context terminated");

    }
}
