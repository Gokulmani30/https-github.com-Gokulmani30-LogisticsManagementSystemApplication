package com.example.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackingResponse {

    private Long id;

    private Long shipmentId;

    private String location;

    private String status;

    private LocalDateTime timestamp;
}

