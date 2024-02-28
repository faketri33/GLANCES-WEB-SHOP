package com.faketri.market.entity.rating.model;

import com.faketri.market.entity.product.model.Product;
import com.faketri.market.entity.user.model.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The type Rating.
 *
 * @author Dmitriy Faketri
 */
@Getter
@Setter
@ToString
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long          id;
    @Column
    private String        description;
    @Column
    private Short         grade;
    @ManyToOne
    private Product       product;
    @ManyToOne
    private Users         users;
    @Column
    private LocalDateTime publishedOn;

    /**
     * Instantiates a new Rating.
     */
    public Rating() {
    }

    /**
     * Instantiates a new Rating.
     *
     * @param id          the id
     * @param description the description
     * @param grade       the grade
     * @param product     the product
     * @param users       the users
     */
    public Rating(Long id, String description, Short grade, Product product,
                  Users users
    ) {
        this.id = id;
        this.description = description;
        this.grade = grade;
        this.product = product;
        this.users = users;
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
        Rating rating = (Rating) o;
        return getId() != null && Objects.equals(getId(), rating.getId());
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
