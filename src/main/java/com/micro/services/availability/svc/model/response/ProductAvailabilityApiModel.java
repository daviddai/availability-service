package com.micro.services.availability.svc.model.response;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductAvailabilityApiModel {

    private String productCode;
    private List<Date> availabilities;
    private List<Date> unavailabilities;

    private ProductAvailabilityApiModel() {}

    @JsonCreator
    public ProductAvailabilityApiModel(@JsonProperty("productCode") String productCode,
                                       @JsonProperty("availabilities") List<Date> availabilities,
                                       @JsonProperty("unavailabilities") List<Date> unavailabilities) {
        this.productCode = productCode;
        this.availabilities = availabilities;
        this.unavailabilities = unavailabilities;
    }

    public String getProductCode() {
        return productCode;
    }

    public List<Date> getAvailabilities() {
        return availabilities;
    }

    public List<Date> getUnavailabilities() {
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

        public Builder withAvailabilities(List<Date> availabilities) {
            productAvailabilityApiModel.availabilities = availabilities;
            return this;
        }

        public Builder withUnavailabilities(List<Date> unavailabilities) {
            productAvailabilityApiModel.unavailabilities = unavailabilities;
            return this;
        }

        public ProductAvailabilityApiModel build() {
            return productAvailabilityApiModel;
        }

    }

}