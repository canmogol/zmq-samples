package com.fererlab.zmq.reqrep;

import org.zeromq.ZMQ;

import java.util.logging.Logger;

public class ReqClient implements Runnable {

    private final Logger L = Logger.getLogger(this.getClass().getSimpleName());

    @Override
    public void run() {
        // create context
        ZMQ.Context context = ZMQ.context(1);
        L.info("context created");

        // socket to talk to server
        ZMQ.Socket requester = context.socket(ZMQ.REQ);
        L.info("socket created");

        // connect to socket
        requester.connect("tcp://localhost:5555");
        L.info("connected to socket");

        // do some request
        for (String requestText : new String[]{"First", "Second", "Third"}) {
            // send request text to server
            requester.send(requestText.getBytes());
            L.info("sent request text: " + requestText);

            // get server's response
            byte[] responseBytes = requester.recv();

            // log response
            L.info("received response: " + new String(responseBytes));
        }

        requester.close();
        L.info("closed requester");

        context.close();
        L.info("context closed");

    }

}
