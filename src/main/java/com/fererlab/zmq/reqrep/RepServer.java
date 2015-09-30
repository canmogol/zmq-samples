package com.fererlab.zmq.reqrep;

import org.zeromq.ZMQ;

import java.util.logging.Logger;

public class RepServer implements Runnable {

    private final Logger L = Logger.getLogger(this.getClass().getSimpleName());

    @Override
    public void run() {

        // sleep 2 seconds, let the client start ahead
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // simply ignore this exception
        }

        // zmq context
        ZMQ.Context context = ZMQ.context(1);
        L.info("created context");

        // socket to talk to client
        ZMQ.Socket responder = context.socket(ZMQ.REP);
        L.info("created socket");

        // bind the socket
        responder.bind("tcp://*:5555");
        L.info("bound to socket");

        // continue until interrupt
        while (!Thread.currentThread().isInterrupted()) {
            // wait for next client to send a request
            byte[] request = responder.recv(0);
            L.info("received request");

            // send OK to client
            responder.send("OK".getBytes());
            L.info("sent response");
        }

        // close the socket
        responder.close();
        L.info("closed socket");

        // terminate the context
        context.term();
        L.info("closed context");
    }
}
