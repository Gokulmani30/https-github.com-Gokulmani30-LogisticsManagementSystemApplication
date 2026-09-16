package com.example.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "warehouses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Warehouse name is required")
	private String name;
	
	@NotBlank(message = "Warehouse location is required")
	private String location;
	
	@Min(value = 1, message = "Capacity must be at least 1")
	private Integer capacity;
	
	@OneToMany(mappedBy = "warehouse")
	private List<Inventory> inventoryItems;
	
}
