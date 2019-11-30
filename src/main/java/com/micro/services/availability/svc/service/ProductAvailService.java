package com.micro.services.availability.svc.service;

import com.micro.services.availability.svc.model.response.ProductAvailabilityApiModel;

import com.micro.services.event.bus.event.ProductAvailabilityUpdated;
import com.micro.services.event.bus.event.model.ProductAvailability;
import com.micro.services.event.bus.subscriber.annotation.EventSubscriber;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductAvailService {

    private Map<String, ProductAvailability> productAvailabilities = new HashMap<>();

    public ProductAvailabilityApiModel getAvailability(String productCode) {

        ProductAvailability productAvailability = productAvailabilities.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(productCode))
                .findAny()
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new RuntimeException("Product code " + productCode + " does not exist"));

        return new ProductAvailabilityApiModel.Builder()
                .withProductCode(productAvailability.getProductCode())
                .withAvailabilities(productAvailability.getAvailabilities())
                .withUnavailabilities(productAvailability.getUnavailabilities())
                .build();
    }

    @EventSubscriber
    public void updateAvailability(ProductAvailabilityUpdated productAvailabilityUpdated) {
        ProductAvailability productAvailability = productAvailabilityUpdated.getProductAvailability();
        productAvailabilities.put(productAvailability.getProductCode(), productAvailability);
    }

}