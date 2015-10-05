package com.fererlab.zmq.parallelpipeline;

import org.zeromq.ZMQ;

import java.util.Random;
import java.util.logging.Logger;

public class Ventilator implements Runnable {

    private final Logger L = Logger.getLogger(this.getClass().getSimpleName());

    @Override
    public void run() {

        // create zmq context
        ZMQ.Context context = ZMQ.context(1);
        L.info("context created");

        // create socket
        ZMQ.Socket sender = context.socket(ZMQ.PUSH);
        L.info("sender PUSH socket created");

        // bind to socket
        sender.bind("tcp://localhost:5558");
        L.info("sender PUSH socket bound");

        //  Initialize random number generator
        Random random = new Random(System.currentTimeMillis());

        //  Send 10 tasks
        for (int taskNumber = 0; taskNumber < 10; taskNumber++) {
            String message = String.valueOf(random.nextInt(100) + 1);
            L.info("current message: " + message);
            sender.send(message, 0);
            L.info("sent message: " + message);
        }
        try {
            //  Give 0MQ time to deliver
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // safely ignore this exception
        }

        // close sender
        sender.close();
        L.info("socket closed");

        // terminate context
        context.term();
        L.info("context terminated");
    }
}
