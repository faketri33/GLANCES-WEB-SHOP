package com.faketri.market.domain.order;

import com.faketri.market.domain.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

    public OrderItem() {
    }

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
