package client.app;

import javax.inject.Singleton;

import io.grpc.ManagedChannel;
import io.micronaut.context.annotation.Factory;
import io.micronaut.grpc.annotation.GrpcChannel;
import server.app.RxExampleServiceGrpc;
import server.app.RxExampleServiceGrpc.RxExampleServiceStub;

@Factory
public class GprcStubConfig {
    @Singleton
    RxExampleServiceStub reactiveStub(
            @GrpcChannel("http://localhost:6565") ManagedChannel channel) {
        return RxExampleServiceGrpc.newRxStub(channel);
    }
}