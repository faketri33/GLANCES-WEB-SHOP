package com.faketri.market.entity.order.model;

import com.faketri.market.entity.user.model.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Orders.
 *
 * @author Dmitriy Faketri
 */
@Getter
@Setter
@ToString
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    @OneToMany
    @ToString.Exclude
    private List<OrderItem> products = new ArrayList<>();

    @Column
    private LocalDateTime dateOfCreate;

    @Column
    private LocalDateTime dateOfRelease;

    @Column
    private Integer price;

    @Enumerated(EnumType.STRING)
    private EStatusOrder statusOrder;

    /**
     * Instantiates a new Orders.
     */
    public Orders() {
    }

    /**
     * Instantiates a new Orders.
     *
     * @param id            the id
     * @param users         the users
     * @param products      the products
     * @param dateOfCreate  the date of create
     * @param dateOfRelease the date of release
     * @param price         the price
     * @param statusOrder   the status order
     */
    public Orders(Long id, Users users, List<OrderItem> products,
                  LocalDateTime dateOfCreate, LocalDateTime dateOfRelease,
                  Integer price, EStatusOrder statusOrder
    ) {
        this.id = id;
        this.users = users;
        this.products = products;
        this.dateOfCreate = dateOfCreate;
        this.dateOfRelease = dateOfRelease;
        this.price = price;
        this.statusOrder = statusOrder;
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
        Orders orders = (Orders) o;
        return getId() != null && Objects.equals(getId(), orders.getId());
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
