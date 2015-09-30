package com.fererlab.zmq;

import com.fererlab.zmq.reqrep.ReqRepSample;

public class RunSamples {

    public static void main(String[] args) {
        RunSamples runSamples = new RunSamples();
        runSamples.start();
    }

    private void start() {
        runZmqReqRes();
    }

    private void runZmqReqRes() {
        ReqRepSample reqRepSample = new ReqRepSample();
        reqRepSample.run();
    }
}
