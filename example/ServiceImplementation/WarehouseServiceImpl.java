package com.example.ServiceImplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.DTO.WarehouseRequest;
import com.example.DTO.WarehouseResponse;
import com.example.Entity.Warehouse;
import com.example.Mapper.WarehouseMapper;
import com.example.Repository.WarehouseRepository;
import com.example.Service.WarehouseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

	private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;

    @Override
    public WarehouseResponse createWarehouse(
            WarehouseRequest request) {

        Warehouse warehouse =
                warehouseMapper.toEntity(request);

        Warehouse savedWarehouse =
                warehouseRepository.save(warehouse);

        return warehouseMapper.toResponse(savedWarehouse);
    }

    @Override
    public WarehouseResponse getWarehouseById(Long id) {

        Warehouse warehouse =
                warehouseRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Warehouse not found with id: " + id
                    )
                );

        return warehouseMapper.toResponse(warehouse);
    }

    @Override
    public List<WarehouseResponse> getAllWarehouses() {

        return warehouseRepository.findAll()
                .stream()
                .map(warehouseMapper::toResponse)
                .toList();
    }

    @Override
    public WarehouseResponse updateWarehouse(
            Long id,
            WarehouseRequest request) {

        Warehouse warehouse =
                warehouseRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Warehouse not found with id: " + id
                    )
                );

        warehouseMapper.updateEntity(
                warehouse,
                request
        );

        Warehouse updatedWarehouse =
                warehouseRepository.save(warehouse);

        return warehouseMapper.toResponse(updatedWarehouse);
    }

    @Override
    public void deleteWarehouse(Long id) {

        if (!warehouseRepository.existsById(id)) {

            throw new RuntimeException(
                "Warehouse not found with id: " + id
            );
        }

        warehouseRepository.deleteById(id);
    }
}
