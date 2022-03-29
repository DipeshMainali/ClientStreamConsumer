package com.example.service;

import io.grpc.ServerBuilder;

import java.io.IOException;

public class ClientSteamServiceStarter {
    public static void startService() throws IOException {
        ServerBuilder.forPort(8081).addService(new ClientStreamServiceImpl()).build().start();
    }
}
