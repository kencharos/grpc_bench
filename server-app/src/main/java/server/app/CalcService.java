package server.app;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Singleton;

@Singleton
public class CalcService {

    public static class P {
        String key;
        long value;
        public P(){}
        public P(String key, long value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }
    }

    public Map<String, Long> sum(List<P> pairs) {
        return pairs
                   .stream()
                   .collect(Collectors.groupingBy(e -> e.key,
                                                  Collectors.mapping(e -> e.value, Collectors.summingLong(e -> e))));
    }

}
