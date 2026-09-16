package com.example.Service;

import java.util.List;

import com.example.DTO.InventoryRequest;
import com.example.DTO.InventoryResponse;

public interface InventoryService {

	InventoryResponse createInventory(InventoryRequest request);
	
	InventoryResponse getInventoryById(Long id);
	
	List<InventoryResponse> getAllInventory();
	
	List<InventoryResponse> getInventoryByWarehouse(Long warehouseId);
	
	InventoryResponse updateInventory(Long id, InventoryRequest request);
	
	void deleteInventory(Long id);
}
