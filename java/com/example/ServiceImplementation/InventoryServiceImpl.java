
package com.example.ServiceImplementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.DTO.InventoryRequest;
import com.example.DTO.InventoryResponse;
import com.example.Entity.Inventory;
import com.example.Entity.Warehouse;
import com.example.Mapper.InventoryMapper;
import com.example.Repository.InventoryRepository;
import com.example.Repository.WarehouseRepository;
import com.example.Service.InventoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    private final WarehouseRepository warehouseRepository;

    private final InventoryMapper inventoryMapper;

    // CREATE INVENTORY
    @Override
    public InventoryResponse createInventory(InventoryRequest request) {

        Warehouse warehouse = warehouseRepository
                .findById(request.getWarehouseId())
                .orElseThrow(() ->
                    new RuntimeException(
                        "Warehouse not found with id: "
                        + request.getWarehouseId()
                    )
                );

        Inventory inventory = inventoryMapper.toEntity(request);

        inventory.setWarehouse(warehouse);

        inventory.setLastUpdated(LocalDateTime.now());

        Inventory savedInventory =
                inventoryRepository.save(inventory);

        return inventoryMapper.toResponse(savedInventory);
    }


    // GET INVENTORY BY ID
	public InventoryResponse getInventoryById(Long id) {

        Inventory inventory =
                inventoryRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Inventory not found with id: " + id
                    )
                );

        return inventoryMapper.toResponse(inventory);
    }


    // GET ALL INVENTORY
    
    public List<InventoryResponse> getAllInventory() {

        return inventoryRepository.findAll()
                .stream()
                .map(inventoryMapper::toResponse)
                .toList();
    }


    // GET INVENTORY BY WAREHOUSE
    @Override
    public List<InventoryResponse> getInventoryByWarehouse(
            Long warehouseId) {

        Warehouse warehouse =
                warehouseRepository.findById(warehouseId)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Warehouse not found with id: "
                        + warehouseId
                    )
                );

        return inventoryRepository
                .findByWarehouse(warehouse)
                .stream()
                .map(inventoryMapper::toResponse)
                .toList();
    }


    // UPDATE INVENTORY
    @Override
    public InventoryResponse updateInventory(
            Long id,
            InventoryRequest request) {

        Inventory inventory =
                inventoryRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Inventory not found with id: " + id
                    )
                );

        Warehouse warehouse =
                warehouseRepository.findById(
                        request.getWarehouseId()
                )
                .orElseThrow(() ->
                    new RuntimeException(
                        "Warehouse not found with id: "
                        + request.getWarehouseId()
                    )
                );

        inventoryMapper.updateEntity(
                inventory,
                request
        );

        inventory.setWarehouse(warehouse);

        inventory.setLastUpdated(LocalDateTime.now());

        Inventory updatedInventory =
                inventoryRepository.save(inventory);

        return inventoryMapper.toResponse(
                updatedInventory
        );
    }


    // DELETE INVENTORY
    @Override
    public void deleteInventory(Long id) {

        if (!inventoryRepository.existsById(id)) {

            throw new RuntimeException(
                "Inventory not found with id: " + id
            );
        }

        inventoryRepository.deleteById(id);
    
}





	

}

