package com.example.Service;

import java.util.List;

import com.example.DTO.ShipmentRequest;
import com.example.DTO.ShipmentResponse;

public interface ShipmentService {

	ShipmentResponse createShipment(ShipmentRequest request);
	
	ShipmentResponse getShipmentById(Long id);
	
	List<ShipmentResponse> getAllShipments();
	
	List<ShipmentResponse> getShipmentsByCustomer(Long customerId);
	
	List<ShipmentResponse> getShipmentsByStatus(String status);
	
	ShipmentResponse updatedShipment(Long id, ShipmentRequest request);
	
	void deleteShipment(Long id);
}
