package com.faketri.market.entity.product.model.child;

import com.faketri.market.entity.product.model.Product;
import com.faketri.market.infastructure.product.dto.CharacteristicsRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The type Characteristics.
 *
 * @author Dmitriy Faketri
 */
@Getter
@Setter
@ToString
@Entity
public class Characteristics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column
    private String       name;
    @Column
    private String       value;
    @ManyToMany(mappedBy = "characteristics", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
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
    public Characteristics(Long id, String name, String value) {
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

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy
                ? ( (HibernateProxy) o ).getHibernateLazyInitializer()
                                        .getPersistentClass()
                : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy
                ? ( (HibernateProxy) this ).getHibernateLazyInitializer()
                                           .getPersistentClass()
                : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Characteristics that = (Characteristics) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ( (HibernateProxy) this ).getHibernateLazyInitializer()
                                           .getPersistentClass()
                                           .hashCode()
                : getClass().hashCode();
    }

}
