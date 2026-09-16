package com.example.Service;

import java.util.List;

import com.example.DTO.TrackingRequest;
import com.example.DTO.TrackingResponse;

public interface TrackingService {

	TrackingResponse createTracking(TrackingRequest request);
	
	TrackingResponse getTrackingById(Long id);

    List<TrackingResponse> getAllTracking();

    List<TrackingResponse> getTrackingByShipment(Long shipmentId);

    TrackingResponse updateTracking(
            Long id,
            TrackingRequest request);

    void deleteTracking(Long id);

}
