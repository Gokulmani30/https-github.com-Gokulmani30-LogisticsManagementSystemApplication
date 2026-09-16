package com.example.Actuator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "logistics")
public class LogisticsEndPoint {

    @ReadOperation
    public Map<String, Object> getStatus() {

        Map<String, Object> response =
                new HashMap<>();

        response.put(
                "application",
                "Logistics Management System");

        response.put(
                "status",
                "UP");

        response.put(
                "message",
                "Logistics system is running successfully");

        return response;
    }
}