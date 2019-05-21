package client.app;

import java.net.URL;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.runtime.Micronaut;
import server.app.ExampleServiceGrpc;
import server.app.InputNumbers;
import server.app.Pair;

public class Application {

    public static void main(String[] args) throws Exception{
        long restElapse = restAccess(100, 1000, "");

        System.out.println("REST(reactive) -> " + restElapse + " nano seconds");
        long restElapse2 = restAccess(100, 1000, "2");

        System.out.println("REST(blocking) -> " + restElapse2 + " nano seconds");

        long grpcElapse = grpcAccess(100, 1000);
        System.out.println("gRPC(blocking) -> " + grpcElapse + " nano seconds");

    }

    private static long restAccess(int size, int num, String suffix) throws Exception {

        long s = System.nanoTime();
        RxHttpClient client = RxHttpClient
                .create(new URL("http://localhost:8081"));

        for(int i =0; i < num; i ++) {

            String v = IntStream.range(0, size).boxed()
                                .map(n ->String.format("{\"key\":\"%s\", \"value\":%s}", (char)((int)'A'+(n%4))+"", n+""))
                                .collect(Collectors.joining(","));
            //System.out.println(v);
            var r = client.exchange(HttpRequest.POST("/api/summing"+suffix,
                                                     String.format("{\"numbers\":[%s]}", v)), String.class)
                          .blockingFirst();
            //System.out.println(r.body());
        }

        return System.nanoTime() - s;
    }

    private static long grpcAccess(int size, int num) throws Exception {

        long s = System.nanoTime();
        ManagedChannel chan = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext().build();

        var stub = ExampleServiceGrpc.newBlockingStub(chan);

        for(int i =0; i < num; i ++) {

            var input = InputNumbers
                    .newBuilder().addAllPairs(
                            IntStream.range(0, size).boxed()
                                     .map(n -> Pair.newBuilder().setKey((char)((int)'A'+(n%4))+"").setValue(n).build())
                                     .collect(Collectors.toList())).build();

            var result = stub.summing(input);

            //System.out.println(result.getResultList());
        }

        return System.nanoTime() - s;
    }


}