package com.micro.services.availability.svc.controller;

import com.micro.services.availability.svc.model.response.ProductAvailabilityApiModel;
import com.micro.services.availability.svc.service.ProductAvailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prod-avail")
class ProductAvailController {

    @Autowired
    private ProductAvailService productAvailService;

    @GetMapping("/{productCode}")
    public ProductAvailabilityApiModel getProductAvailabilities(@PathVariable("productCode") String productCode) {
        return productAvailService.getAvailability(productCode);
    }

}