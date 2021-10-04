package com.klevu.task.model;

import java.util.Objects;

public class ProductPair {
    private final String productId;
    private final String buyWithProductId;

    public ProductPair(String productId, String buyWithProductId) {
        this.productId = productId;
        this.buyWithProductId = buyWithProductId;
    }

    public String getProductId() {
        return productId;
    }

    public String getBuyWithProductId() {
        return buyWithProductId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductPair that = (ProductPair) o;
        return productId.equals(that.productId) &&
                buyWithProductId.equals(that.buyWithProductId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, buyWithProductId);
    }
}