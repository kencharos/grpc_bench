package server.app;

import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.grpc.stub.StreamObserver;
import server.app.CalcService.P;

@Singleton
public class CalcGrpc extends ExampleServiceGrpc.ExampleServiceImplBase {

    @Inject CalcService service;

    @Override
    public void summing(InputNumbers request, StreamObserver<Result> responseObserver) {

        Map<String, Long> result = service.sum(request.getPairsList().stream().map(p -> new P(p.getKey(), p.getValue())).collect(
                Collectors.toList()));
        responseObserver.onNext(Result.newBuilder().addAllResult(result.entrySet()
               .stream().map(p -> Pair.newBuilder().setKey(p.getKey()).setValue(p.getValue()).build()).collect(Collectors.toList())).build());
        responseObserver.onCompleted();

    }
}
