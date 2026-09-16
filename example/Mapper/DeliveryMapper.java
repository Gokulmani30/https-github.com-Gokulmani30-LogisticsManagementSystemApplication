package com.example.Mapper;

import org.springframework.stereotype.Component;

import com.example.DTO.DeliveryRequest;
import com.example.DTO.DeliveryResponse;
import com.example.Entity.Delivery;

@Component
public class DeliveryMapper {

    // Request → Entity
    public Delivery toEntity(DeliveryRequest request) {

        Delivery delivery = new Delivery();

        delivery.setDeliveredBy(
            request.getDeliveredBy()
        );
        delivery.setDeliveryAddress(
        	    request.getDeliveryAddress()
        	);
        delivery.setStatus(
            request.getStatus()
        );

        
        // Service will automatically set it.

        return delivery;
    }

    // Entity → Response
    public DeliveryResponse toResponse(Delivery delivery) {

        DeliveryResponse response = new DeliveryResponse();

        response.setId(delivery.getId());

        if (delivery.getShipment() != null) {
            response.setShipmentId(
                delivery.getShipment().getId()
            );
        }

        // Show automatically generated date
        response.setDeliveryDate(
            delivery.getDeliveryDate()
        );

        response.setDeliveredBy(
            delivery.getDeliveredBy()
        );
        
        response.setDeliveryAddress(
        	    delivery.getDeliveryAddress()
        	);
        
        response.setStatus(
            delivery.getStatus()
        );

        return response;
    }

    // Update Entity
    public void updateEntity(
            Delivery delivery,
            DeliveryRequest request) {

        delivery.setDeliveredBy(
            request.getDeliveredBy()
        );

        delivery.setStatus(
            request.getStatus()
        );


        delivery.setDeliveryAddress(
            request.getDeliveryAddress()
        );
        // It should remain the original delivery creation date.
    }
}