package com.example.ServiceImplementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.DTO.DeliveryRequest;
import com.example.DTO.DeliveryResponse;
import com.example.Entity.Delivery;
import com.example.Entity.Shipment;
import com.example.Mapper.DeliveryMapper;
import com.example.Repository.DeliveryRepository;
import com.example.Repository.ShipmentRepository;
import com.example.Service.DeliveryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final ShipmentRepository shipmentRepository;
    private final DeliveryMapper deliveryMapper;

    @Override
    public DeliveryResponse createDelivery(
            DeliveryRequest request) {

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

        Delivery delivery =
                deliveryMapper.toEntity(request);

        delivery.setShipment(shipment);

        // Automatically set current date and time
        delivery.setDeliveryDate(LocalDateTime.now());

        Delivery savedDelivery =
                deliveryRepository.save(delivery);

        return deliveryMapper.toResponse(savedDelivery);
    }

    @Override
    public DeliveryResponse getDeliveryById(Long id) {

        Delivery delivery =
                deliveryRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Delivery not found with id: " + id
                    )
                );

        return deliveryMapper.toResponse(delivery);
    }

    @Override
    public List<DeliveryResponse> getAllDeliveries() {

        return deliveryRepository.findAll()
                .stream()
                .map(deliveryMapper::toResponse)
                .toList();
    }

    @Override
    public DeliveryResponse getDeliveryByShipment(
            Long shipmentId) {

        Shipment shipment =
                shipmentRepository.findById(shipmentId)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Shipment not found with id: "
                        + shipmentId
                    )
                );

        Delivery delivery =
                deliveryRepository.findByShipment(shipment)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Delivery not found for shipment: "
                        + shipmentId
                    )
                );

        return deliveryMapper.toResponse(delivery);
    }

    @Override
    public DeliveryResponse updateDelivery(
            Long id,
            DeliveryRequest request) {

        Delivery delivery =
                deliveryRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Delivery not found with id: " + id
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

        deliveryMapper.updateEntity(
                delivery,
                request
        );

        delivery.setShipment(shipment);

        // Do NOT change deliveryDate during update

        Delivery updatedDelivery =
                deliveryRepository.save(delivery);

        return deliveryMapper.toResponse(
                updatedDelivery
        );
    }

    @Override
    public void deleteDelivery(Long id) {

        if (!deliveryRepository.existsById(id)) {

            throw new RuntimeException(
                "Delivery not found with id: " + id
            );
        }

        deliveryRepository.deleteById(id);
    }
}