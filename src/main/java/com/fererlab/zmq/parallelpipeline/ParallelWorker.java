package com.fererlab.zmq.parallelpipeline;

import org.zeromq.ZMQ;

import java.util.logging.Logger;

public class ParallelWorker implements Runnable {

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
        receiver.connect("tcp://localhost:5558");
        L.info("receiver PULL socket connected");

        // create push socket
        ZMQ.Socket sender = context.socket(ZMQ.PUSH);
        L.info("sender PUSH socket created");

        // bind to socket
        sender.connect("tcp://localhost:5559");
        L.info("sender PUSH socket bound");

        //  Process tasks forever
        while (!Thread.currentThread().isInterrupted()) {
            L.info(Thread.currentThread().getId() + " will receive next message");
            // receive message
            String message = new String(receiver.recv(0)).trim();
            L.info(Thread.currentThread().getId() + " received message: " + message);

            // send message to sink
            sender.send(message);
            L.info(Thread.currentThread().getId() + " sent message: " + message);
        }

        // close socket
        receiver.close();
        L.info("socket closed");

        // close sender socket
        sender.close();
        L.info("sender socket closed");

        // terminate context
        context.term();
        L.info("context terminated");
    }

}
