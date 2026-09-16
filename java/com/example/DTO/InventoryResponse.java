package com.example.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponse {

	 private Long id;

	    private Long warehouseId;

	    private String productName;

	    private Integer quantity;

	    private LocalDateTime lastUpdated;
}
