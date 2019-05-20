package server.app;

import java.util.List;

import javax.inject.Inject;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.reactivex.Single;

@Controller
public class CalcController {

    @Inject
    private CalcService service;

    @Post("/api/summing")
    public Single<ResponseResult> sum(@Body Single<RequestNumbers> nums) {
        return nums.map(RequestNumbers::getNumbers)
                   .map(service::sum)
                   .map(ResponseResult::new);
    }

    public static class RequestNumbers {
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
