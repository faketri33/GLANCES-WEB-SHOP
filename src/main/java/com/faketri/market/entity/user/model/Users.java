package com.faketri.market.entity.user.model;

import com.faketri.market.entity.order.model.Orders;
import com.faketri.market.entity.product.model.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The type Users.
 *
 * @author Dmitriy Faketri
 */
@Getter
@Setter
@ToString
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column
    private String email;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String city;

    @ElementCollection(targetClass = ERole.class)
    @CollectionTable(name = "user_role",
                     joinColumns = @JoinColumn(name = "users_id"))
    private Set<ERole> role = new HashSet<>();

    @OneToMany
    @JoinColumn
    @ToString.Exclude
    private Set<Orders> orders = new HashSet<>();

    @OneToMany
    @ToString.Exclude
    private Set<Product> favoriteProduct = new HashSet<>();

    @Column
    private LocalDateTime dateOfCreate;

    /**
     * Instantiates a new Users.
     */
    public Users() {
    }

    /**
     * Instantiates a new Users.
     *
     * @param id       the id
     * @param email    the email
     * @param login    the login
     * @param password the password
     * @param city     the city
     */
    public Users(Long id, String email, String login, String password,
                 String city
    ) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.password = password;
        this.city = city;
    }

    @PrePersist
    private void onCreate() {
        dateOfCreate = LocalDateTime.now();
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
        Users users = (Users) o;
        return getId() != null && Objects.equals(getId(), users.getId());
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
