package com.faketri.market.entity.order.model;

import com.faketri.market.entity.product.model.Product;
import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * The type Order item.
 *
 * @author Dmitriy Faketri
 */
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
    @OneToOne
    private Product product;
    @Column
    private Integer count;
    @Column
    private BigDecimal price;

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
    public OrderItem(UUID id, Product product, Integer count, BigDecimal price) {
        this.id = id;
        this.product = product;
        this.count = count;
        this.price = price;
    }

    @PrePersist
    private void setPrice() {
        price = product.getPrice().multiply(BigDecimal.valueOf(count));
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy
                ? ((HibernateProxy) o).getHibernateLazyInitializer()
                .getPersistentClass()
                : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer()
                .getPersistentClass()
                : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        OrderItem orderItem = (OrderItem) o;
        return getId() != null && Objects.equals(getId(), orderItem.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer()
                .getPersistentClass()
                .hashCode()
                : getClass().hashCode();
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", product=" + product +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}
