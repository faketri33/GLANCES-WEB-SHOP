package com.faketri.market.entity.user.payload.user.model;

import com.faketri.market.entity.image.model.Image;
import com.faketri.market.entity.product.payload.product.model.Product;
import com.faketri.market.entity.user.payload.basket.model.Basket;
import com.faketri.market.entity.user.payload.order.model.Orders;
import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * The type Users.
 *
 * @author Dmitriy Faketri
 */

@Entity
@Table
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    private Image profileImage =
            new Image(null, "images/user/profile/default_profile_image.png");

    @Column
    private String name;

    @Column
    private String surname;

    @Column(unique = true)
    private String login;

    @Column
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Basket basket = new Basket();

    @Column
    private LocalDateTime dateOfBirthday;

    @Column
    private String city;

    @ElementCollection(targetClass = ERole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "users_id"))
    private Set<ERole> role = new HashSet<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<Orders> orders = new HashSet<>();

    @ManyToMany
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
    public Users(UUID id, String email, String login, String password,
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


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Image getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Image profileImage) {
        this.profileImage = profileImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public LocalDateTime getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(LocalDateTime dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<ERole> getRole() {
        return role;
    }

    public void setRole(Set<ERole> role) {
        this.role = role;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public Set<Product> getFavoriteProduct() {
        return favoriteProduct;
    }

    public void setFavoriteProduct(Set<Product> favoriteProduct) {
        this.favoriteProduct = favoriteProduct;
    }

    public LocalDateTime getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(LocalDateTime dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
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
        Users users = (Users) o;
        return getId() != null && Objects.equals(getId(), users.getId());
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
        return "Users{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", profileImage=" + profileImage +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirthday=" + dateOfBirthday +
                ", city='" + city + '\'' +
                ", dateOfCreate=" + dateOfCreate +
                '}';
    }
}
