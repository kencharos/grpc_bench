package server.app;

import java.util.List;

import javax.inject.Singleton;

@Singleton
public class CalcService {

    public long sum(List<Integer> numbers) {
        return numbers.stream().mapToLong(i -> i).sum();
    }

}
