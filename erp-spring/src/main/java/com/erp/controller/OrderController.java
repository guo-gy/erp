package com.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.erp.entity.Product;
import com.erp.entity.Order;
import com.erp.service.OrderService;
import com.erp.entity.Company;
import com.erp.entity.Inventory;
import com.erp.service.CompanyService;
import com.erp.service.InventoryService;
import com.erp.service.ProductService;
import com.erp.utils.JsonResponse;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private ProductService productService;

    @PostMapping("/addorder")
    public JsonResponse<Integer> addOrder(@RequestBody AddOrderRequest request) {
        JsonResponse<Integer> response = new JsonResponse<Integer>(false, "", null);
        Company origin = companyService.getCompanyByName(request.originName);
        Company target = companyService.getCompanyByName(request.targetName);
        if (origin == null) {
            response.message = "卖方公司不存在";
            return response;
        }
        if (target == null) {
            response.message = "买方公司不存在";
            return response;
        }
        Product product = productService.getProductByName(request.productName);
        if (product == null) {
            response.message = "产品不存在";
            return response;
        }
        Inventory originInventory = inventoryService.getInventoryByCompanyIdAndProductId(origin.getId(),
                product.getId());
        Inventory targetInventory = inventoryService.getInventoryByCompanyIdAndProductId(target.getId(),
                product.getId());
        if (originInventory.getQuantity() == null) {
            response.message = "卖方库存不足";
            return response;
        }
        if (originInventory.getQuantity() < request.quantity) {
            response.message = "卖方库存不足";
            return response;
        }
        if (target.getMoney() == null) {
            response.message = "买方余额不足";
            return response;
        }
        if (target.getMoney() < request.quantity * request.price) {
            response.message = "买方余额不足";
            return response;
        }
        Order order = orderService.addOrder(product.getId(), origin.getId(), target.getId(), request.price,
                request.quantity);
        if(targetInventory==null){
            targetInventory = inventoryService.addInventory(target.getId(), product.getId(), 0);
        }

        companyService.updCompany(order.getId(), origin.getMoney() + request.quantity * request.price);
        companyService.updCompany(target.getId(), target.getMoney() - request.quantity * request.price);
        inventoryService.updInventory(originInventory.getId(), originInventory.getQuantity() - request.quantity);
        inventoryService.updInventory(targetInventory.getId(), targetInventory.getQuantity() + request.quantity);
        response.success = true;
        response.message = "添加订单成功";
        response.data = order.getId();
        return response;
    }

    @GetMapping("/{companyId}/origin")
    public JsonResponse<List<Order>> getOriginOrder(@PathVariable("companyId") Integer companyId) {
        JsonResponse<List<Order>> response = new JsonResponse<List<Order>>(false, "获取失败", null);
        try {
            List<Order> order = orderService.getOrderByOriginId(companyId);
            response.success = true;
            response.message = "获取卖单成功";
            response.data = order;
        } catch (Exception e) {
            response.success = false;
            response.message = e.getMessage();
        }
        return response;
    }

    @GetMapping("/{companyId}/target")
    public JsonResponse<List<Order>> getTargetOrder(@PathVariable("companyId") Integer companyId) {
        JsonResponse<List<Order>> response = new JsonResponse<List<Order>>(false, "获取失败", null);
        try {
            List<Order> order = orderService.getOrderByTargetId(companyId);
            response.success = true;
            response.message = "获取买单成功";
            response.data = order;
        } catch (Exception e) {
            response.success = false;
            response.message = e.getMessage();
        }
        return response;
    }

    public static class AddOrderRequest {
        public String productName;
        public String originName;
        public String targetName;
        public Integer quantity;
        public Double price;
    }

}
