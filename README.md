# zmq-samples
ZeroMQ Samples project, see [RunSamples](https://github.com/canmogol/zmq-samples/blob/master/src/main/java/com/fererlab/zmq/RunSamples.java) for examples

### Examples

There are examples of Request Response, Publish Subscribe and Parallel Pipeline under [ReqRepSample](https://github.com/canmogol/zmq-samples/blob/master/src/main/java/com/fererlab/zmq/reqrep/ReqRepSample.java), [PubSubSample](https://github.com/canmogol/zmq-samples/blob/master/src/main/java/com/fererlab/zmq/pubsub/PubSubSample.java) and [ParallelPipelineSample](https://github.com/canmogol/zmq-samples/blob/master/src/main/java/com/fererlab/zmq/parallelpipeline/ParallelPipelineSample.java) classes

### Dependency
project depends on pure java implementation of the zeromq library
```xml
<dependency>
    <groupId>org.zeromq</groupId>
    <artifactId>jeromq</artifactId>
    <version>0.3.5</version>
</dependency>
```
### Client/Server
There are client and server implementations for Request/Response and Publish/Subscribe examples.

### Pipeline
The parallel pipeline project does not have any client or server, it simply generates a message from Ventilator, a Worker gets message and forwards it to a Sink, it is useful for load balancing, work distribution.
