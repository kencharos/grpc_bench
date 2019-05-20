package server.app;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.grpc.stub.StreamObserver;

@Singleton
public class CalcGrpc extends ExampleServiceGrpc.ExampleServiceImplBase {

    @Inject CalcService service;

    @Override
    public void summing(InputNumbers request, StreamObserver<Result> responseObserver) {

        long result = service.sum(request.getNumbersList());
        responseObserver.onNext(Result.newBuilder().setSum(result).build());
        responseObserver.onCompleted();

    }
}
