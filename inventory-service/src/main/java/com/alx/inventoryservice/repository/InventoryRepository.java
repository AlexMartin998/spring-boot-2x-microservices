package com.alx.inventoryservice.repository;

import com.alx.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    boolean existsBySkuCode(String skuCode);

    List<Inventory> findBySkuCodeIn(List<String> skuCodeList);

}
