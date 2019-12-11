package com.micro.services.availability.svc.model.response;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductAvailabilityApiModel {

    private String productCode;
    private List<LocalDate> availabilities;
    private List<LocalDate> unavailabilities;

    private ProductAvailabilityApiModel() {}

    @JsonCreator
    public ProductAvailabilityApiModel(@JsonProperty("productCode") String productCode,
                                       @JsonProperty("availabilities") List<LocalDate> availabilities,
                                       @JsonProperty("unavailabilities") List<LocalDate> unavailabilities) {
        this.productCode = productCode;
        this.availabilities = availabilities;
        this.unavailabilities = unavailabilities;
    }

    public String getProductCode() {
        return productCode;
    }

    public List<LocalDate> getAvailabilities() {
        return availabilities;
    }

    public List<LocalDate> getUnavailabilities() {
        return unavailabilities;
    }

    public static class Builder {

        private ProductAvailabilityApiModel productAvailabilityApiModel;

        public Builder() {
            productAvailabilityApiModel = new ProductAvailabilityApiModel();
        }

        public Builder withProductCode(String productCode) {
            productAvailabilityApiModel.productCode = productCode;
            return this;
        }

        public Builder withAvailabilities(List<LocalDate> availabilities) {
            productAvailabilityApiModel.availabilities = availabilities;
            return this;
        }

        public Builder withUnavailabilities(List<LocalDate> unavailabilities) {
            productAvailabilityApiModel.unavailabilities = unavailabilities;
            return this;
        }

        public ProductAvailabilityApiModel build() {
            return productAvailabilityApiModel;
        }

    }

}