package com.faketri.market.entity.product.payload.product.model;

import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.UUID;

/**
 * The type Order item.
 *
 * @author Dmitriy Faketri
 */
@Entity
public class ProductItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
    @ManyToOne
    private Product product;
    @Column
    private Integer quantity;
    @Column
    private Integer price;

    /**
     * Instantiates a new Order item.
     */
    public ProductItem() {
    }

    /**
     * Instantiates a new Order item.
     *
     * @param id       the id
     * @param product  the product
     * @param quantity the count
     */
    public ProductItem(UUID id, Product product, Integer quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    @PrePersist
    private void setPrice() {
        price = product.getPrice() * quantity;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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
        ProductItem productItem = (ProductItem) o;
        return getId() != null && Objects.equals(getId(), productItem.getId());
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
        return "ProductItem{" +
                "id=" + id +
                ", product=" + product +
                ", count=" + quantity +
                ", price=" + price +
                '}';
    }
}
