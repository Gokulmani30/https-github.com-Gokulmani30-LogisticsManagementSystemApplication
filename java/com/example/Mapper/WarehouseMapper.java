package com.example.Mapper;

import org.springframework.stereotype.Component;

import com.example.DTO.WarehouseRequest;
import com.example.DTO.WarehouseResponse;
import com.example.Entity.Warehouse;

@Component
public class WarehouseMapper {

	public Warehouse toEntity(WarehouseRequest request) {

        Warehouse warehouse = new Warehouse();

        warehouse.setName(request.getName());
        warehouse.setLocation(request.getLocation());
        warehouse.setCapacity(request.getCapacity());

        return warehouse;
    }

    public WarehouseResponse toResponse(Warehouse warehouse) {

        WarehouseResponse response = new WarehouseResponse();

        response.setId(warehouse.getId());
        response.setName(warehouse.getName());
        response.setLocation(warehouse.getLocation());
        response.setCapacity(warehouse.getCapacity());

        return response;
    }

    public void updateEntity(
            Warehouse warehouse,
            WarehouseRequest request) {

        warehouse.setName(request.getName());
        warehouse.setLocation(request.getLocation());
        warehouse.setCapacity(request.getCapacity());
    }
}
