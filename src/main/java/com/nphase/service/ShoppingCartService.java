package com.nphase.service;

import com.nphase.entity.ProductConfiguration;
import com.nphase.entity.ShoppingCart;

import java.math.BigDecimal;
import java.util.Map;

public class ShoppingCartService {

    public BigDecimal calculateTotalPrice(ShoppingCart shoppingCart) {
        ProductConfiguration productConfiguration = shoppingCart.getProductConfiguration();
        return shoppingCart.getProducts()
                .stream()
                .map(product -> {
                    if (product.getQuantity() > productConfiguration.getItemCount()) {
                        return calculateDiscount(calculatePrice(product.getPricePerUnit(), product.getQuantity()), productConfiguration.getDiscount());
                    }
                    return calculatePrice(product.getPricePerUnit(), product.getQuantity());
                })
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public BigDecimal calculateTotalPriceWithCategories(ShoppingCart shoppingCart) {
        Map<String, Integer> productCategoriesCount = shoppingCart.getProductCategories();
        ProductConfiguration productConfiguration = shoppingCart.getProductConfiguration();
        return shoppingCart.getProducts()
                .stream()
                .map(product -> {
                    if (productCategoriesCount.get(product.getCategory()) > productConfiguration.getItemCount()) {
                        return calculateDiscount(calculatePrice(product.getPricePerUnit(), product.getQuantity()), productConfiguration.getDiscount());
                    }
                    return calculatePrice(product.getPricePerUnit(), product.getQuantity());
                })
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }


    public BigDecimal calculateDiscount(BigDecimal price, BigDecimal discountValue) {
        return price.subtract(((price.multiply(discountValue)).divide(new BigDecimal("100"))));
    }

    public BigDecimal calculatePrice(BigDecimal price, int quantity) {
        return price.multiply(BigDecimal.valueOf(quantity));
    }


}
