package com.fererlab.zmq;

import com.fererlab.zmq.pubsub.PubSubSample;
import com.fererlab.zmq.reqrep.ReqRepSample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunSamples {

    ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        RunSamples runSamples = new RunSamples();
        runSamples.start();
    }

    private void start() {
        //runZmqReqRes();
        runZmqPubSub();
    }

    private void runZmqReqRes() {
        ReqRepSample reqRepSample = new ReqRepSample();
        executorService.execute(reqRepSample);
    }

    private void runZmqPubSub() {
        PubSubSample pubSubSample = new PubSubSample();
        executorService.execute(pubSubSample);
    }

}
