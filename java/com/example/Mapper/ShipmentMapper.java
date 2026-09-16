package com.example.Mapper;

import org.springframework.stereotype.Component;

import com.example.DTO.ShipmentRequest;
import com.example.DTO.ShipmentResponse;
import com.example.Entity.Shipment;

@Component
public class ShipmentMapper {
	
	public Shipment toEntity(ShipmentRequest request) {

        Shipment shipment = new Shipment();

        shipment.setOrigin(request.getOrigin());
        shipment.setDestination(request.getDestination());
        shipment.setStatus(request.getStatus());

        return shipment;
    }

    public ShipmentResponse toResponse(Shipment shipment) {

        ShipmentResponse response = new ShipmentResponse();

        response.setId(shipment.getId());

        if (shipment.getCustomer() != null) {
            response.setCustomerId(
                shipment.getCustomer().getCustomerId()
            );
        }

        response.setOrigin(shipment.getOrigin());
        response.setDestination(shipment.getDestination());
        response.setStatus(shipment.getStatus());
        response.setShipmentDate(shipment.getShipmentDate());

        return response;
    }

    public void updateEntity(
            Shipment shipment,
            ShipmentRequest request) {

        shipment.setOrigin(request.getOrigin());
        shipment.setDestination(request.getDestination());
        shipment.setStatus(request.getStatus());
    }

}
