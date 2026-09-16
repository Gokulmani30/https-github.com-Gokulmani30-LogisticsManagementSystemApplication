package com.example.Mapper;

import org.springframework.stereotype.Component;

import com.example.DTO.TrackingRequest;
import com.example.DTO.TrackingResponse;
import com.example.Entity.Tracking;

@Component
public class TrackingMapper {

	public Tracking toEntity(TrackingRequest request) {

        Tracking tracking = new Tracking();

        tracking.setLocation(request.getLocation());
        tracking.setStatus(request.getStatus());

        return tracking;
    }

    public TrackingResponse toResponse(Tracking tracking) {

        TrackingResponse response = new TrackingResponse();

        response.setId(tracking.getId());

        if (tracking.getShipment() != null) {
            response.setShipmentId(
                tracking.getShipment().getId()
            );
        }

        response.setLocation(tracking.getLocation());
        response.setStatus(tracking.getStatus());
        response.setTimestamp(tracking.getTimestamp());

        return response;
    }

    public void updateEntity(
            Tracking tracking,
            TrackingRequest request) {

        tracking.setLocation(request.getLocation());
        tracking.setStatus(request.getStatus());
    }
}
