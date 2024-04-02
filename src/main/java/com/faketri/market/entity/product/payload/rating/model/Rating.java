package com.faketri.market.entity.product.payload.rating.model;

import com.faketri.market.entity.product.payload.product.model.Product;
import com.faketri.market.entity.user.payload.user.model.Users;
import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * The type Rating.
 *
 * @author Dmitriy Faketri
 */
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column
    private String description;
    @Column
    private Short grade;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Users users;
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
    public Rating(UUID id, String description, Short grade, Product product,
                  Users users
    ) {
        this.id = id;
        this.description = description;
        this.grade = grade;
        this.product = product;
        this.users = users;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getGrade() {
        return grade;
    }

    public void setGrade(Short grade) {
        this.grade = grade;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public LocalDateTime getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(LocalDateTime publishedOn) {
        this.publishedOn = publishedOn;
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
        Rating rating = (Rating) o;
        return getId() != null && Objects.equals(getId(), rating.getId());
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
        return "Rating{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", grade=" + grade +
                ", product=" + product +
                ", users=" + users +
                ", publishedOn=" + publishedOn +
                '}';
    }
}
