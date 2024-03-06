package com.faketri.market.entity.order.model;

import com.faketri.market.entity.user.model.Users;
import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * The type Orders.
 *
 * @author Dmitriy Faketri
 */
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    @OneToMany
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
    public Orders(UUID id, Users users, List<OrderItem> products,
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public List<OrderItem> getProducts() {
        return products;
    }

    public void setProducts(List<OrderItem> products) {
        this.products = products;
    }

    public LocalDateTime getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(LocalDateTime dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public LocalDateTime getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(LocalDateTime dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public EStatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(EStatusOrder statusOrder) {
        this.statusOrder = statusOrder;
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
        Orders order = (Orders) o;
        return getId() != null && Objects.equals(getId(), order.getId());
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
        return "Orders{" +
                "id=" + id +
                ", users=" + users +
                ", dateOfCreate=" + dateOfCreate +
                ", dateOfRelease=" + dateOfRelease +
                ", price=" + price +
                ", statusOrder=" + statusOrder +
                '}';
    }
}
