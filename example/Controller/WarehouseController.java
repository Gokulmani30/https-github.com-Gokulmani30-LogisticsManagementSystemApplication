package com.example.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.WarehouseRequest;
import com.example.DTO.WarehouseResponse;
import com.example.Service.WarehouseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/warehouses")
@RequiredArgsConstructor
@Validated
public class WarehouseController {

    private final WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<WarehouseResponse> createWarehouse(
            @Valid @RequestBody WarehouseRequest request) {

        WarehouseResponse response =
                warehouseService.createWarehouse(request);

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseResponse> getWarehouseById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                warehouseService.getWarehouseById(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<WarehouseResponse>> getAllWarehouses() {

        return ResponseEntity.ok(
                warehouseService.getAllWarehouses()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<WarehouseResponse> updateWarehouse(
            @PathVariable Long id,
            @Valid @RequestBody WarehouseRequest request) {

        return ResponseEntity.ok(
                warehouseService.updateWarehouse(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWarehouse(
            @PathVariable Long id) {

        warehouseService.deleteWarehouse(id);

        return ResponseEntity.ok(
                "Warehouse deleted successfully"
        );
    }
}