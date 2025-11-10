package com.ecommerce.inventory_service.utils;

import com.ecommerce.inventory_service.dto.InventoryRequest;
import com.ecommerce.inventory_service.dto.InventoryResponse;
import com.ecommerce.inventory_service.entity.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    Inventory requestToEntity(InventoryRequest inventoryRequest);

    InventoryRequest responseToRequest(InventoryResponse inventoryResponse);
}
