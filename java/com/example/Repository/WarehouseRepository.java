package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.Warehouse;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse,Long>{

}
