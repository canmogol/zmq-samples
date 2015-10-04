package com.fererlab.zmq.pubsub;

import org.zeromq.ZMQ;

import java.util.logging.Logger;

public class SubClient implements Runnable {

    private final Logger L = Logger.getLogger(this.getClass().getSimpleName());

    @Override
    public void run() {
        // create ZMQ Context
        ZMQ.Context context = ZMQ.context(1);
        L.info("context crated");

        // create socket
        ZMQ.Socket subscriber = context.socket(ZMQ.SUB);
        L.info("socket created");

        // connect to socket
        subscriber.connect("tcp://*:5556");
        L.info("connected to socket");

        String filter = "5";
        subscriber.subscribe(filter.getBytes());

        //  Process 100 updates
        for (int update_nbr = 0; update_nbr < 10; update_nbr++) {
            String read = subscriber.recvStr(0);
            L.info("read: " + read);
        }
        L.info("read all of them");

        // close the socket
        subscriber.close();
        L.info("closed socket");

        // terminate the context
        context.term();
        L.info("closed context");

    }

}
