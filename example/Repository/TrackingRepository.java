package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.Shipment;
import com.example.Entity.Tracking;

@Repository
public interface TrackingRepository extends JpaRepository<Tracking, Long> {

	List<Tracking> findByShipment(Shipment shipment);

    List<Tracking> findByStatus(String status);
}
