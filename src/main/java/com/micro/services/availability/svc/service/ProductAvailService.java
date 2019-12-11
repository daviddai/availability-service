package com.micro.services.availability.svc.service;

import com.micro.services.availability.svc.model.response.ProductAvailabilityApiModel;

import com.micro.services.event.bus.event.ProductAvailabilityUpdated;
import com.micro.services.event.bus.event.model.ProductAvailability;
import com.micro.services.event.bus.event.model.ProductAvailabilityRule;
import com.micro.services.event.bus.subscriber.annotation.EventSubscriber;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
                .withAvailabilities(getDatesByRules(productAvailability.getAvailabilityRules()))
                .withUnavailabilities(getDatesByRules(productAvailability.getUnavailabilityRules()))
                .build();
    }

    private List<LocalDate> getDatesByRules(List<ProductAvailabilityRule> productAvailabilityRules) {
        return productAvailabilityRules.stream()
                .map(productAvailabilityRule ->
                        this.getDatesBetween(productAvailabilityRule.getStartDate(), productAvailabilityRule.getEndDate()))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        return IntStream.iterate(0, i -> i + 1)
                .limit(ChronoUnit.DAYS.between(startDate, endDate))
                .mapToObj(startDate::plusDays)
                .collect(Collectors.toList());
    }

    @EventSubscriber
    public void updateAvailability(ProductAvailabilityUpdated productAvailabilityUpdated) {
        ProductAvailability productAvailability = productAvailabilityUpdated.getProductAvailability();
        productAvailabilities.put(productAvailability.getProductCode(), productAvailability);
    }

}