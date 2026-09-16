package com.example.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentResponse {

    private Long id;
    private Long customerId;
    private String origin;
    private String destination;
    private String status;
    private LocalDateTime shipmentDate;
    
}