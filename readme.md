# Benchmark gRPC, Rest with micronaut

+ clinet-app->(HTTP)->server-app
+ clinet-app->(gRPC)->server-app

Result of 1000 sequensial request.Result of 1000 sequensial request.

REST(reactive) -> 4841930676 nano seconds
REST(blocking) -> 4564804338 nano seconds
gRPC(blocking) -> 1036080800 nano seconds
