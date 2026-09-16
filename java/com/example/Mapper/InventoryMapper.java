package com.example.Mapper;

import org.springframework.stereotype.Component;

import com.example.DTO.InventoryRequest;
import com.example.DTO.InventoryResponse;
import com.example.Entity.Inventory;

@Component
public class InventoryMapper {

	 public Inventory toEntity(InventoryRequest request) {

	        Inventory inventory = new Inventory();

	        inventory.setProductName(request.getProductName());
	        inventory.setQuantity(request.getQuantity());

	        return inventory;
	    }

	    public InventoryResponse toResponse(Inventory inventory) {

	        InventoryResponse response = new InventoryResponse();

	        response.setId(inventory.getId());

	        if (inventory.getWarehouse() != null) {
	            response.setWarehouseId(
	                inventory.getWarehouse().getId()
	            );
	        }

	        response.setProductName(inventory.getProductName());
	        response.setQuantity(inventory.getQuantity());
	        response.setLastUpdated(inventory.getLastUpdated());

	        return response;
	    }

	    public void updateEntity(
	            Inventory inventory,
	            InventoryRequest request) {

	        inventory.setProductName(request.getProductName());
	        inventory.setQuantity(request.getQuantity());
	    }
}
