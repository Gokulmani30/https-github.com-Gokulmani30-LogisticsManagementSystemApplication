package com.example.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentRequest {
	
	 @NotNull(message = "Customer ID is required")
	    private Long customerId;

	    @NotBlank(message = "Origin is required")
	    private String origin;

	    @NotBlank(message = "Destination is required")
	    private String destination;

	    @NotBlank(message = "Status is required")
	    private String status;

	    
}
