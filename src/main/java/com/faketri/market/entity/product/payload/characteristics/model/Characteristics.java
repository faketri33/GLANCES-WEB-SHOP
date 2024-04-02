package com.faketri.market.entity.product.payload.characteristics.model;

import com.faketri.market.entity.product.payload.product.model.Product;
import com.faketri.market.infastructure.product.payload.characteristics.dto.CharacteristicsRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * The type Characteristics.
 *
 * @author Dmitriy Faketri
 */
@Entity
public class Characteristics {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column
    private String name;
    @Column
    private String value;
    @ManyToMany(mappedBy = "characteristics", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Product> products = new HashSet<>();

    /**
     * Instantiates a new Characteristics.
     */
    public Characteristics() {
    }

    /**
     * Instantiates a new Characteristics.
     *
     * @param id    the id
     * @param name  the name
     * @param value the value
     */
    public Characteristics(UUID id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    /**
     * Instantiates a new Characteristics.
     *
     * @param characteristicsRequest the characteristics request
     */
    public Characteristics(CharacteristicsRequest characteristicsRequest) {
        name = characteristicsRequest.getName();
        value = characteristicsRequest.getValue();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
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
        Characteristics that = (Characteristics) o;
        return getId() != null && Objects.equals(getId(), that.getId());
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
        return "Characteristics{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
