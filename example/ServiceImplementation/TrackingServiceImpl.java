package com.example.ServiceImplementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.DTO.TrackingRequest;
import com.example.DTO.TrackingResponse;
import com.example.Entity.Shipment;
import com.example.Entity.Tracking;
import com.example.Mapper.TrackingMapper;
import com.example.Repository.ShipmentRepository;
import com.example.Repository.TrackingRepository;
import com.example.Service.TrackingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrackingServiceImpl implements TrackingService {

    private final TrackingRepository trackingRepository;
    private final ShipmentRepository shipmentRepository;
    private final TrackingMapper trackingMapper;

    @Override
    public TrackingResponse createTracking(
            TrackingRequest request) {

        Shipment shipment =
                shipmentRepository.findById(
                        request.getShipmentId()
                )
                .orElseThrow(() ->
                    new RuntimeException(
                        "Shipment not found with id: "
                        + request.getShipmentId()
                    )
                );

        Tracking tracking =
                trackingMapper.toEntity(request);

        tracking.setShipment(shipment);

        // Automatically create current date and time
        tracking.setTimestamp(LocalDateTime.now());

        Tracking savedTracking =
                trackingRepository.save(tracking);

        return trackingMapper.toResponse(savedTracking);
    }

    @Override
    public TrackingResponse getTrackingById(Long id) {

        Tracking tracking =
                trackingRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Tracking not found with id: " + id
                    )
                );

        return trackingMapper.toResponse(tracking);
    }

    @Override
    public List<TrackingResponse> getAllTracking() {

        return trackingRepository.findAll()
                .stream()
                .map(trackingMapper::toResponse)
                .toList();
    }

    @Override
    public List<TrackingResponse> getTrackingByShipment(
            Long shipmentId) {

        Shipment shipment =
                shipmentRepository.findById(shipmentId)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Shipment not found with id: "
                        + shipmentId
                    )
                );

        return trackingRepository
                .findByShipment(shipment)
                .stream()
                .map(trackingMapper::toResponse)
                .toList();
    }

    @Override
    public TrackingResponse updateTracking(
            Long id,
            TrackingRequest request) {

        Tracking tracking =
                trackingRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Tracking not found with id: " + id
                    )
                );

        Shipment shipment =
                shipmentRepository.findById(
                        request.getShipmentId()
                )
                .orElseThrow(() ->
                    new RuntimeException(
                        "Shipment not found with id: "
                        + request.getShipmentId()
                    )
                );

        trackingMapper.updateEntity(
                tracking,
                request
        );

        tracking.setShipment(shipment);

        // Do NOT change timestamp during update

        Tracking updatedTracking =
                trackingRepository.save(tracking);

        return trackingMapper.toResponse(
                updatedTracking
        );
    }

    @Override
    public void deleteTracking(Long id) {

        if (!trackingRepository.existsById(id)) {

            throw new RuntimeException(
                "Tracking not found with id: " + id
            );
        }

        trackingRepository.deleteById(id);
    }
}