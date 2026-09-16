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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.ShipmentRequest;
import com.example.DTO.ShipmentResponse;
import com.example.Service.ShipmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/shipments")
@RequiredArgsConstructor
@Validated
public class ShipmentController {

    private final ShipmentService shipmentService;

    @PostMapping
    public ResponseEntity<ShipmentResponse> createShipment(
            @Valid @RequestBody ShipmentRequest request) {

        ShipmentResponse response =
                shipmentService.createShipment(request);

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShipmentResponse> getShipmentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                shipmentService.getShipmentById(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<ShipmentResponse>> getAllShipments() {

        return ResponseEntity.ok(
                shipmentService.getAllShipments()
        );
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ShipmentResponse>>
            getShipmentsByCustomer(
                    @PathVariable Long customerId) {

        return ResponseEntity.ok(
                shipmentService
                        .getShipmentsByCustomer(customerId)
        );
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ShipmentResponse>>
            getShipmentsByStatus(
                    @PathVariable String status) {

        return ResponseEntity.ok(
                shipmentService
                        .getShipmentsByStatus(status)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShipmentResponse> updateShipment(
            @PathVariable Long id,
            @Valid @RequestBody ShipmentRequest request) {

        return ResponseEntity.ok(
                shipmentService.updatedShipment(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShipment(
            @PathVariable Long id) {

        shipmentService.deleteShipment(id);

        return ResponseEntity.ok(
                "Shipment deleted successfully"
        );
    }
}