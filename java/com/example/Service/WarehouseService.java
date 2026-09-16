package com.example.Service;

import java.util.List;

import com.example.DTO.WarehouseRequest;
import com.example.DTO.WarehouseResponse;

public interface WarehouseService {

	WarehouseResponse createWarehouse(WarehouseRequest request);
	
	WarehouseResponse getWarehouseById(Long id);
	
	List<WarehouseResponse> getAllWarehouses();
	
	WarehouseResponse updateWarehouse(Long id, WarehouseRequest request);
	
	void deleteWarehouse(Long id);
	
}
