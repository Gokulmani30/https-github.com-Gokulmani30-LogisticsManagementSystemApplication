package com.example.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseRequest {

	@NotBlank(message = "Warehouse name is required")
    private String name;

    @NotBlank(message = "Warehouse location is required")
    private String location;

    @Min(value = 1, message = "Capacity must be at least 1")
    private Integer capacity;
}
