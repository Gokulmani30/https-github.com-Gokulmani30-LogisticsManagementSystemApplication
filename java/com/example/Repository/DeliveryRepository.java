package com.example.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.Delivery;
import com.example.Entity.Shipment;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
Optional<Delivery> findByShipment(Shipment shipment);

List<Delivery> findByStatus(String status);
}
