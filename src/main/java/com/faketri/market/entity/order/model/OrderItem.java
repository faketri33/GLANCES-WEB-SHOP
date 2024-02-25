package com.faketri.market.entity.order.model;

import com.faketri.market.entity.product.model.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/**
 * The type Order item.
 *
 * @author Dmitriy Faketri
 */
@Setter
@Getter
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long    id;
    @OneToOne
    private Product product;
    @Column
    private Integer count;
    @Column
    private Long    price;

    /**
     * Instantiates a new Order item.
     */
    public OrderItem() {
    }

    /**
     * Instantiates a new Order item.
     *
     * @param id      the id
     * @param product the product
     * @param count   the count
     * @param price   the price
     */
    public OrderItem(Long id, Product product, Integer count, Long price) {
        this.id = id;
        this.product = product;
        this.count = count;
        this.price = price;
    }

    @PrePersist
    private void setPrice() {
        price = product.getPrice() * count;
    }


}
