package server.app;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.reactivex.Single;
import server.app.CalcService.P;

@Controller
public class CalcController {

    @Inject
    private CalcService service;

    @Post("/api/summing")
    public Single<ResponseResult> sum(@Body Single<RequestNumbers> nums) {
        return nums.map(RequestNumbers::getNumbers)
                   .map(service::sum)
                   .map(r -> r.entrySet().stream().map(e -> new P(e.getKey(), e.getValue())).collect(
                           Collectors.toList())).map(ResponseResult::new);
    }
    @Post("/api/summing2")
    public ResponseResult sum(@Body RequestNumbers nums) {

        Map<String, Long> ans = service.sum(nums.numbers);
        return new ResponseResult(ans.entrySet().stream().map(e -> new P(e.getKey(), e.getValue())).collect(
                Collectors.toList()));

    }

    public static class RequestNumbers {
        private List<P> numbers;

        public List<P> getNumbers() {
            return numbers;
        }

        public void setNumbers(List<P> numbers) {
            this.numbers = numbers;
        }
    }
    public static class ResponseResult {
        public ResponseResult(){}
        public ResponseResult(List<P> res) {
            this.result = res;
        }
        private List<P> result;

        public List<P> getResult() {
            return result;
        }

        public void setResult(List<P> result) {
            this.result = result;
        }
    }






}
