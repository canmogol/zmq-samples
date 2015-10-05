package com.fererlab.zmq.parallelpipeline;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelPipelineSample implements Runnable {

    private ExecutorService executorService;
    private Ventilator ventilator;
    private Sink sink;
    private ParallelWorker worker1;
    private ParallelWorker worker2;

    public ParallelPipelineSample() {
        executorService = Executors.newFixedThreadPool(4);
        worker1 = new ParallelWorker();
        worker2 = new ParallelWorker();
        ventilator = new Ventilator();
        sink = new Sink();
    }

    @Override
    public void run() {
        executorService.execute(worker1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.execute(ventilator);
        executorService.execute(sink);
        executorService.execute(worker2);
    }

}
