

ab -n 1000 -c 2 -m POST http://localhost:8080/rest/80
ab -n 1000 -c 2 -m POST http://localhost:8080/grpc/80

