package com.alx.inventoryservice.repository;

import com.alx.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    boolean existsBySkuCode(String skuCode);

}
