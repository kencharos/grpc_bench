package client.app;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.inject.Inject;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;
import server.app.InputNumbers;
import server.app.Result;
import server.app.RxExampleServiceGrpc.RxExampleServiceStub;

@Controller
public class ClientController {

    @Client("http://localhost:8081") @Inject RxHttpClient httpClient;

    @Inject
    RxExampleServiceStub stub;

    @Post("rest/{size}")
    public Single<ResponseResult> rest(int size) {
        return httpClient.retrieve(HttpRequest.POST("/api/summing", new RequestNumbers(randomNums(size))), ResponseResult.class)
                  .singleOrError();
    }

    @Post("grpc/{size}")
    public Single<ResponseResult> grpc(int size) {
        return stub.summing(InputNumbers.newBuilder().addAllNumbers(randomNums(size)).build())
                   .map(Result::getSum)
                   .map(ResponseResult::new);
    }

    private List<Integer> randomNums(int size) {
        return IntStream.range(1, size).boxed().collect(Collectors.toList());
    }

    public static class RequestNumbers {
        public RequestNumbers(){}

        public RequestNumbers(List<Integer> numbers) {
            this.numbers = numbers;
        }

        private List<Integer> numbers;

        public List<Integer> getNumbers() {
            return numbers;
        }

        public void setNumbers(List<Integer> numbers) {
            this.numbers = numbers;
        }
    }
    public static class ResponseResult {
        public ResponseResult(){}
        public ResponseResult(long res) {
            this.result = res;
        }
        private long result;

        public long getResult() {
            return result;
        }

        public void setResult(long result) {
            this.result = result;
        }
    }

}


