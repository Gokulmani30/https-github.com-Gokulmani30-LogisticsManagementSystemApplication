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

import com.example.DTO.DeliveryRequest;
import com.example.DTO.DeliveryResponse;
import com.example.Service.DeliveryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/deliveries")
@RequiredArgsConstructor
@Validated
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<DeliveryResponse> createDelivery(
            @Valid @RequestBody DeliveryRequest request) {

        DeliveryResponse response =
                deliveryService.createDelivery(request);

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryResponse> getDeliveryById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                deliveryService.getDeliveryById(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<DeliveryResponse>>
            getAllDeliveries() {

        return ResponseEntity.ok(
                deliveryService.getAllDeliveries()
        );
    }

    @GetMapping("/shipment/{shipmentId}")
    public ResponseEntity<DeliveryResponse>
            getDeliveryByShipment(
                    @PathVariable Long shipmentId) {

        return ResponseEntity.ok(
                deliveryService
                        .getDeliveryByShipment(shipmentId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryResponse> updateDelivery(
            @PathVariable Long id,
            @Valid @RequestBody DeliveryRequest request) {

        return ResponseEntity.ok(
                deliveryService.updateDelivery(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDelivery(
            @PathVariable Long id) {

        deliveryService.deleteDelivery(id);

        return ResponseEntity.ok(
                "Delivery deleted successfully"
        );
    }
}