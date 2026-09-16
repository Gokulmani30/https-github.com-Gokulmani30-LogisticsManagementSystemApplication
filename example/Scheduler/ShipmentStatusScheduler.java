package com.example.Scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.Entity.Shipment;
import com.example.Repository.ShipmentRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShipmentStatusScheduler {

    private final ShipmentRepository shipmentRepository;

    @Scheduled(fixedRate = 60000)
    public void updateShipmentStatus() {

        LocalDateTime now = LocalDateTime.now();

        List<Shipment> shipments = shipmentRepository.findAll();

        for (Shipment shipment : shipments) {

            if (shipment.getShipmentDate() != null
                    && shipment.getShipmentDate().isBefore(now)
                    && !"Delivered".equalsIgnoreCase(shipment.getStatus())) {

                shipment.setStatus("In Transit");

                shipmentRepository.save(shipment);
            }
        }
    }
}