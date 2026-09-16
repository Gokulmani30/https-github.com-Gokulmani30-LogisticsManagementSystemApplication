package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.Inventory;
import com.example.Entity.Warehouse;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{

	List<Inventory> findByWarehouse(Warehouse warehouse);

    List<Inventory> findByProductName(String productName);

}
