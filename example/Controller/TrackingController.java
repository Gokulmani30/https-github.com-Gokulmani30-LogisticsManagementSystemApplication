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

import com.example.DTO.TrackingRequest;
import com.example.DTO.TrackingResponse;
import com.example.Service.TrackingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tracking")
@RequiredArgsConstructor
@Validated
public class TrackingController {

    private final TrackingService trackingService;

    @PostMapping
    public ResponseEntity<TrackingResponse> createTracking(
            @Valid @RequestBody TrackingRequest request) {

        TrackingResponse response =
                trackingService.createTracking(request);

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrackingResponse> getTrackingById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                trackingService.getTrackingById(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<TrackingResponse>> getAllTracking() {

        return ResponseEntity.ok(
                trackingService.getAllTracking()
        );
    }

    @GetMapping("/shipment/{shipmentId}")
    public ResponseEntity<List<TrackingResponse>>
            getTrackingByShipment(
                    @PathVariable Long shipmentId) {

        return ResponseEntity.ok(
                trackingService
                        .getTrackingByShipment(shipmentId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrackingResponse> updateTracking(
            @PathVariable Long id,
            @Valid @RequestBody TrackingRequest request) {

        return ResponseEntity.ok(
                trackingService.updateTracking(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTracking(
            @PathVariable Long id) {

        trackingService.deleteTracking(id);

        return ResponseEntity.ok(
                "Tracking deleted successfully"
        );
    }
}