# Benchmark gRPC, Rest with micronaut

+ REST (HTTP)->clinet-app->(HTTP)->server-app
+ gRPC (HTTP)->clinet-app->(gRPC)->server-app

## gRPC

ab -n 1000 -c 2 -m POST http://localhost:8080/grpc/80

```
Server Software:
Server Hostname:        localhost
Server Port:            8080

Document Path:          /grpc/80
Document Length:        15 bytes

Concurrency Level:      2
Time taken for tests:   3.196 seconds
Complete requests:      1000
Failed requests:        0
Total transferred:      142000 bytes
HTML transferred:       15000 bytes
Requests per second:    312.86 [#/sec] (mean)
Time per request:       6.393 [ms] (mean)
Time per request:       3.196 [ms] (mean, across all concurrent requests)
Transfer rate:          43.38 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    1   1.4      1      31
Processing:     1    2   1.4      2      29
Waiting:        1    1   1.3      1      27
Total:          1    4   2.2      3      34

Percentage of the requests served within a certain time (ms)
  50%      3
  66%      4
  75%      4
  80%      4
  90%      5
  95%      6
  98%      6
  99%      7
 100%     34 (longest request)
```

## REST


ab -n 1000 -c 2 -m POST http://localhost:8080/rest/80

```
Document Path:          /rest/80
Document Length:        15 bytes

Concurrency Level:      2
Time taken for tests:   8.642 seconds
Complete requests:      1000
Failed requests:        0
Total transferred:      142000 bytes
HTML transferred:       15000 bytes
Requests per second:    115.71 [#/sec] (mean)
Time per request:       17.284 [ms] (mean)
Time per request:       8.642 [ms] (mean, across all concurrent requests)
Transfer rate:          16.05 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        1    3   1.3      3       7
Processing:     6   12   3.5     11      33
Waiting:        5   11   3.2     10      32
Total:          7   14   4.5     13      36

Percentage of the requests served within a certain time (ms)
  50%     13
  66%     15
  75%     17
  80%     18
  90%     21
  95%     24
  98%     25
  99%     27
 100%     36 (longest request)
```
