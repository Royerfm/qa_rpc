package com.ejemplo;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class HelloWorldServer {
    public static void main(String[] args) throws Exception {
        // Crear servidor en puerto 50051
        Server server = ServerBuilder.forPort(50051)
            .addService(new GreeterImpl())
            .build();
        
        System.out.println("Servidor iniciado...");
        server.start();
        server.awaitTermination();
    }

    // Implementación del servicio Greeter
    static class GreeterImpl extends GreeterGrpc.GreeterImplBase {
        @Override
        public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
            // Crear respuesta
            HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hola " + req.getName())
                .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}
