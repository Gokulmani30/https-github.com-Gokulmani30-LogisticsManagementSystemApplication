package com.example.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryResponse {

    private Long id;

    private Long shipmentId;
    
    private String deliveryAddress;

    private LocalDateTime deliveryDate;

    private String deliveredBy;

    private String status;
    
}
