package com.ejemplo;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class HelloWorldClient {
    public static void main(String[] args) throws Exception {
        // Crear canal de comunicación con el servidor
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
            .usePlaintext()
            .build();
        
        // Crear stub
        GreeterGrpc.GreeterStub stub = GreeterGrpc.newStub(channel);
        
        // Crear solicitud
        HelloRequest request = HelloRequest.newBuilder()
            .setName("Mundo")
            .build();
        
        // Enviar solicitud y recibir respuesta
        stub.sayHello(request, new StreamObserver<HelloReply>() {
            @Override
            public void onNext(HelloReply value) {
                System.out.println(value.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                channel.shutdown();
            }
        });
        
        // Esperar para que el cliente reciba la respuesta
        Thread.sleep(2000);
    }
}
