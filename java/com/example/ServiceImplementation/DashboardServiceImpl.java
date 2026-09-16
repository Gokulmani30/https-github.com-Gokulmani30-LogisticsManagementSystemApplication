package com.example.ServiceImplementation;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.Repository.CustomerRepository;
import com.example.Repository.WarehouseRepository;
import com.example.Repository.InventoryRepository;
import com.example.Repository.ShipmentRepository;
import com.example.Repository.TrackingRepository;
import com.example.Repository.DeliveryRepository;
import com.example.Service.DashboardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl
        implements DashboardService {

    private final CustomerRepository customerRepository;

    private final WarehouseRepository warehouseRepository;

    private final InventoryRepository inventoryRepository;

    private final ShipmentRepository shipmentRepository;

    private final TrackingRepository trackingRepository;

    private final DeliveryRepository deliveryRepository;

    @Override
    public Map<String, Long> getDashboard() {

        Map<String, Long> result =
                new LinkedHashMap<>();

        result.put(
                "totalCustomers",
                customerRepository.count());

        result.put(
                "totalWarehouses",
                warehouseRepository.count());

        result.put(
                "totalInventory",
                inventoryRepository.count());

        result.put(
                "totalShipments",
                shipmentRepository.count());

        result.put(
                "totalTracking",
                trackingRepository.count());

        result.put(
                "totalDeliveries",
                deliveryRepository.count());

        return result;
    }
}