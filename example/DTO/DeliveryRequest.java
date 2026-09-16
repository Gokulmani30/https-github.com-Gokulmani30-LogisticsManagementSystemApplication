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
public class DeliveryRequest {

	 @NotNull(message = "Shipment ID is required")
	    private Long shipmentId;

	    @NotBlank(message = "Delivery Address is required")
	    private String deliveryAddress;

	    @NotBlank(message = "Delivered by is required")
	    private String deliveredBy;

	    @NotBlank(message = "Delivery status is required")
	    private String status;
}
