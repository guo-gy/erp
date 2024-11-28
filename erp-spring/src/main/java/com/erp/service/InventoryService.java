package com.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.Inventory;
import com.erp.repository.InventoryRepository;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    // 增
    public Inventory addInventory(Integer companyId, Integer productId, Integer quatity) {
        Inventory inventory = new Inventory();
        inventory.setCompanyId(companyId);
        inventory.setProductId(productId);
        inventory.setQuantity(quatity);
        inventoryRepository.save(inventory);
        return inventory;
    }

    // 删
    public void delInventory(Integer id) {
        inventoryRepository.deleteById(id);
    }

    // 改
    public void updInventory(Integer id, Integer quatity) {
        Inventory inventory = inventoryRepository.findById(id).orElse(null);
        if (inventory != null) {
            inventory.setQuantity(quatity);
            inventoryRepository.save(inventory);
        }
    }

    // 查
    public Inventory getInventoryById(Integer inventoryId) {
        return inventoryRepository.findById(inventoryId).orElse(null);
    }

    public Inventory getInventoryByCompanyIdAndProductId(Integer companyId, Integer productId) {
        for (Inventory inventory : inventoryRepository.findByCompanyId(companyId)) {
            if (inventory.getProductId().equals(productId)) {
                return inventory;
            }
        }
        return null;
    }

    public List<Inventory> getAllProducts(Integer companyId) {
        return inventoryRepository.findByCompanyId(companyId);
    }

}
