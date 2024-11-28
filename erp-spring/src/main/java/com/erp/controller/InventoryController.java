package com.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.erp.entity.Inventory;
import com.erp.entity.Product;
import com.erp.service.InventoryService;
import com.erp.service.ProductService;
import com.erp.utils.JsonResponse;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService; // 注入InventoryService
    @Autowired
    private ProductService productService; // 注入ProductService

    @PostMapping("/updinventory")
    public JsonResponse<Integer> updInventory(@RequestBody updInventoryRequest request) {
        JsonResponse<Integer> response = new JsonResponse<Integer>(false, "", null);
        Product product = productService.getProductByName(request.productName);
        if (product == null) {
            product = productService.addProduct(request.productName);
        }
        Inventory inventory = inventoryService.getInventoryByCompanyIdAndProductId(request.companyId, product.getId());
        if (inventory == null) {
            inventory = inventoryService.addInventory(request.companyId, product.getId(), request.quantity);
            response.success = true;
            response.message = "添加库存成功";
        } else {
            inventoryService.updInventory(inventory.getId(), request.quantity);
            response.success = true;
            response.message = "更新库存成功";
        }

        return response;
    }

    @PostMapping("/delinventory")
    public JsonResponse<Integer> delInventory(@RequestBody delInventoryRequest request) {
        JsonResponse<Integer> response = new JsonResponse<Integer>(false, "", null);
        try {
            inventoryService.delInventory(request.id);
            response.success = true;
            response.message = "删除库存成功";
        } catch (Exception e) {
            response.success = false;
            response.message = e.getMessage();
        }
        return response;
    }

    @GetMapping("/{companyId}/{productId}/quantity")
    public JsonResponse<Integer> getInventoryQuantity(@PathVariable("companyId") Integer companyId,
            @PathVariable("productId") Integer productId) {
        JsonResponse<Integer> response = new JsonResponse<Integer>(false, "获取失败", null);
        try {
            Inventory inventory = inventoryService.getInventoryByCompanyIdAndProductId(companyId, productId);
            response.success = true;
            response.message = "获取库存数量成功";
            response.data = inventory.getQuantity();
        } catch (Exception e) {
            response.success = false;
            response.message = e.getMessage();
        }
        return response;
    }

    @GetMapping("/{companyId}")
    public JsonResponse<List<Inventory>> getAllInventory(@PathVariable("companyId") Integer companyId) {
        JsonResponse<List<Inventory>> response = new JsonResponse<List<Inventory>>(false, "获取失败", null);
        try {
            List<Inventory> inventory = inventoryService.getAllProducts(companyId);
            response.success = true;
            response.message = "获取库存成功";
            response.data = inventory;
        } catch (Exception e) {
            response.success = false;
            response.message = e.getMessage();
        }
        return response;
    }

    public static class updInventoryRequest {
        public Integer companyId;
        public String productName;
        public Integer quantity;
    }

    public static class delInventoryRequest {
        public Integer id;
    }
}
