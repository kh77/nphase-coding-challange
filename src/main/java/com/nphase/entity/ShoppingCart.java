package com.nphase.entity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingCart {
    private final List<Product> products;
    private final ProductConfiguration productConfiguration;

    public ShoppingCart(List<Product> products, ProductConfiguration productConfiguration) {
        this.products = products;
        this.productConfiguration = productConfiguration;
    }

    public ProductConfiguration getProductConfiguration() {
        return productConfiguration;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Map<String, Integer> getProductCategories() {
        return products.stream().collect(
                Collectors.groupingBy(Product::getCategory, Collectors.summingInt(Product::getQuantity)));

    }

}
