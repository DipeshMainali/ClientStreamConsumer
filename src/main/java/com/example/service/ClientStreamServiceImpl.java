package com.example.service;

import com.example.util.BODMAS;
import io.grpc.stub.StreamObserver;
import service.ClientStream.*;
import service.ClientStreamServiceGrpc;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientStreamServiceImpl extends ClientStreamServiceGrpc.ClientStreamServiceImplBase {
    private static final Logger logger = Logger.getLogger(ClientStreamServiceImpl.class.getName());

    ClientStreamServiceImpl() {
    }

    @Override
    public StreamObserver<Request> startStream(StreamObserver<Response> responseObserver) {
        return new StreamObserver<>() {
            List<Request> requests = new ArrayList<>();

            @Override
            public void onNext(Request value) {
                requests.add(value);
                logger.info("request from client : " + value.getNumber() + " rule : " + value.getRule());
            }

            @Override
            public void onError(Throwable t) {
                logger.log(Level.INFO, t.getMessage());
                responseObserver.onError(t);
            }

            @Override
            public void onCompleted() {
                double total = 0.0;
                String expression = "";
                for (Request request : requests) {
                    switch (request.getRule()) {
                        case MULTIPLY:
                            expression = expression + request.getNumber() + "*";
                            break;
                        case DIVIDE:
                            expression = expression + request.getNumber() + "/";
                            break;
                        case ADD:
                            expression = expression + request.getNumber() + "+";
                            break;
                        case SUBTRACT:
                            expression = expression + request.getNumber() + "-";
                            break;
                        case EQUAL:
                            logger.info("Expression : " + expression + request.getNumber());
                            total = BODMAS.evaluate(expression + request.getNumber());
                            if (-1111111 == total) {
                                logger.info("Invalid Expression : " + total);
                                responseObserver.onError(new Throwable("Invalid Expression"));
                            }
                            expression = String.valueOf(total);
                            break;
                    }
                }
                responseObserver.onNext(Response.newBuilder().setTotal(total).build());
                responseObserver.onCompleted();
            }
        };
    }
}
