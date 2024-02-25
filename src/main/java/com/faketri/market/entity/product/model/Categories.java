package com.faketri.market.entity.product.model;

import com.faketri.market.entity.image.model.Image;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.SqlTypes;

import java.util.Objects;

/**
 * The type Categories.
 *
 * @author Dmitriy Faketri
 */
@Getter
@Setter
@ToString
@Entity
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long   id;
    @Column
    private String name;
    @OneToOne()
    private Image  image;

    /**
     * Instantiates a new Categories.
     */
    public Categories() {
    }

    /**
     * Instantiates a new Categories.
     *
     * @param id    the id
     * @param name  the name
     * @param image the image
     */
    public Categories(Long id, String name, Image image) {
        this.id = id;
        this.name = name;
        this.image = image;
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
        Categories that = (Categories) o;
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
