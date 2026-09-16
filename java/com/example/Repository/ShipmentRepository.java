package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.Customer;
import com.example.Entity.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

	List<Shipment> findByCustomer(Customer customer);

    List<Shipment> findByStatus(String status);

    List<Shipment> findByOrigin(String origin);

    List<Shipment> findByDestination(String destination);
}
