package com.micro.services.availability.svc.controller;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prod-avail")
class ProductAvailabilityController {

    @GetMapping("/{productCode}")
    public void getProductAvailabilities(@PathParam("productCode") String productCode) {
        
    }

}