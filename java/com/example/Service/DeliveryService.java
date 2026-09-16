package com.example.Service;

import java.util.List;

import com.example.DTO.DeliveryRequest;
import com.example.DTO.DeliveryResponse;

public interface DeliveryService {

	DeliveryResponse createDelivery(DeliveryRequest request);

    DeliveryResponse getDeliveryById(Long id);

    List<DeliveryResponse> getAllDeliveries();

    DeliveryResponse getDeliveryByShipment(Long shipmentId);

    DeliveryResponse updateDelivery(
            Long id,
            DeliveryRequest request);

    void deleteDelivery(Long id);
}
