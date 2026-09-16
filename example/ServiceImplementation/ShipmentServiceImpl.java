package com.example.ServiceImplementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.DTO.ShipmentRequest;
import com.example.DTO.ShipmentResponse;
import com.example.Entity.Customer;
import com.example.Entity.Shipment;
import com.example.Mapper.ShipmentMapper;
import com.example.Repository.CustomerRepository;
import com.example.Repository.ShipmentRepository;
import com.example.Service.ShipmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final CustomerRepository customerRepository;
    private final ShipmentMapper shipmentMapper;

    @Override
    public ShipmentResponse createShipment(ShipmentRequest request) {

        Customer customer =
                customerRepository.findById(request.getCustomerId())
                .orElseThrow(() ->
                    new RuntimeException(
                        "Customer not found with id: "
                        + request.getCustomerId()
                    )
                );

        Shipment shipment =
                shipmentMapper.toEntity(request);

        shipment.setCustomer(customer);

        // Automatically set current date and time
        shipment.setShipmentDate(LocalDateTime.now());

        Shipment savedShipment =
                shipmentRepository.save(shipment);

        return shipmentMapper.toResponse(savedShipment);
    }

    @Override
    public ShipmentResponse getShipmentById(Long id) {

        Shipment shipment =
                shipmentRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Shipment not found with id: " + id
                    )
                );

        return shipmentMapper.toResponse(shipment);
    }

    @Override
    public List<ShipmentResponse> getAllShipments() {

        return shipmentRepository.findAll()
                .stream()
                .map(shipmentMapper::toResponse)
                .toList();
    }

    @Override
    public List<ShipmentResponse> getShipmentsByCustomer(
            Long customerId) {

        Customer customer =
                customerRepository.findById(customerId)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Customer not found with id: "
                        + customerId
                    )
                );

        return shipmentRepository
                .findByCustomer(customer)
                .stream()
                .map(shipmentMapper::toResponse)
                .toList();
    }

    @Override
    public List<ShipmentResponse> getShipmentsByStatus(
            String status) {

        return shipmentRepository
                .findByStatus(status)
                .stream()
                .map(shipmentMapper::toResponse)
                .toList();
    }

    @Override
    public ShipmentResponse updatedShipment(
            Long id,
            ShipmentRequest request) {

        Shipment shipment =
                shipmentRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Shipment not found with id: " + id
                    )
                );

        Customer customer =
                customerRepository.findById(
                        request.getCustomerId()
                )
                .orElseThrow(() ->
                    new RuntimeException(
                        "Customer not found with id: "
                        + request.getCustomerId()
                    )
                );

        shipmentMapper.updateEntity(
                shipment,
                request
        );

        shipment.setCustomer(customer);

        Shipment updatedShipment =
                shipmentRepository.save(shipment);

        return shipmentMapper.toResponse(
                updatedShipment
        );
    }

    @Override
    public void deleteShipment(Long id) {

        if (!shipmentRepository.existsById(id)) {

            throw new RuntimeException(
                "Shipment not found with id: " + id
            );
        }

        shipmentRepository.deleteById(id);
    }
}