package com.faketri.market.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PromotionItem {
    @Column
    private Product product;
    @Column
    private int discount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromotionItem that = (PromotionItem) o;
        return discount == that.discount && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, discount);
    }
}
