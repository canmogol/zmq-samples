package com.fererlab.zmq.reqrep;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReqRepSample implements Runnable {

    private ExecutorService executorService;
    private ReqClient reqClient;
    private RepServer repServer;

    public ReqRepSample() {
        executorService = Executors.newFixedThreadPool(2);
        reqClient = new ReqClient();
        repServer = new RepServer();
    }

    public void run() {
        executorService.execute(repServer);
        executorService.execute(reqClient);
    }

}
