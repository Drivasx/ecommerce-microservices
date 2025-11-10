package com.ecommerce.inventory_service.service;

import com.ecommerce.inventory_service.dto.InventoryRequest;
import com.ecommerce.inventory_service.dto.InventoryResponse;
import com.ecommerce.inventory_service.entity.Inventory;
import com.ecommerce.inventory_service.exception.InsufficientStockException;
import com.ecommerce.inventory_service.exception.ResourceNotFoundException;
import com.ecommerce.inventory_service.repository.InventoryRepository;
import com.ecommerce.inventory_service.utils.InventoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    private InventoryMapper mapper;

    public Inventory getByProductSku(String productSku){
        return inventoryRepository.getByProductSku(productSku).orElseThrow(()-> new ResourceNotFoundException("Product is not in the inventory"));
    }

    public List<Inventory> getAllInventory(){
        return inventoryRepository.findAll();
    }

    public Inventory createInventoryRegistry(InventoryRequest request){
        Inventory inventory = mapper.requestToEntity(request);
        return  inventoryRepository.save(inventory);
    }



    public void reduceStock(String productSku, int quantity){
        Inventory inventory = getByProductSku(productSku);

        if(inventory.getStock() < quantity){
            throw new InsufficientStockException("Insufficient stock");
        }

        inventory.setStock(inventory.getStock() - quantity);
        inventory.setLastUpdated(Instant.now());
        inventoryRepository.save(inventory);
    }

    public void increaseStock(String productSku, int quantity){
        Inventory inventory = getByProductSku(productSku);

        inventory.setStock(inventory.getStock() + quantity);
        inventory.setLastUpdated(Instant.now());
        inventoryRepository.save(inventory);
    }


}
