package com.example.Entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shipments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "customer_id",nullable = false)
	private Customer customer;
	
	@NotBlank(message = "Origin is required")
	private String origin;
	
	@NotBlank(message = "Destination is required")
	private String destination;
	
	private String status;
	
	private LocalDateTime shipmentDate;

	@OneToMany(mappedBy = "shipment")
	private List<Tracking> trackingUpdates;
	
	@OneToOne(mappedBy = "shipment")
	private Delivery delivery;
}
