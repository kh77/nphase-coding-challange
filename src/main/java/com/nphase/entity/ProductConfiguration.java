package com.nphase.entity;

import java.math.BigDecimal;

public class ProductConfiguration {
    private final int itemCount;
    private final BigDecimal discount;

    public ProductConfiguration(int itemCount, BigDecimal dicount) {
        this.itemCount = itemCount;
        this.discount = dicount;
    }

    public int getItemCount() {
        return itemCount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }
}
