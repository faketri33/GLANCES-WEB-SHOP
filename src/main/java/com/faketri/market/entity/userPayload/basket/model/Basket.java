package com.faketri.market.entity.userPayload.basket.model;

import com.faketri.market.entity.productPayload.product.model.ProductItem;
import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
    @OneToMany(cascade = CascadeType.ALL)
    List<ProductItem> products = new ArrayList<>();
    @Column
    private Integer price;

    public Basket() {
    }

    public Basket(UUID id, List<ProductItem> products, Integer price) {
        this.id = id;
        this.products = products;
        this.price = price;
    }

    @PrePersist
    @PreUpdate
    private void updatePrice() {
        products.forEach(product -> price = price + product.getPrice());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<ProductItem> getProducts() {
        return products;
    }

    public void setProducts(List<ProductItem> products) {
        this.products = products;
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
        Basket basket = (Basket) o;
        return getId() != null && Objects.equals(getId(), basket.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer()
                .getPersistentClass()
                .hashCode()
                : getClass().hashCode();
    }
}
