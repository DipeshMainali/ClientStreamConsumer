package com.example;

import com.example.service.ClientSteamServiceStarter;
import io.micronaut.runtime.Micronaut;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
        try {
            ClientSteamServiceStarter.startService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}