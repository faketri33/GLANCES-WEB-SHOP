package com.faketri.market.infastructure.user.dto;

import com.faketri.market.entity.basket.model.Basket;
import com.faketri.market.entity.image.model.Image;
import com.faketri.market.entity.order.model.Orders;
import com.faketri.market.entity.product.model.Product;
import com.faketri.market.entity.user.model.ERole;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * The type User response.
 *
 * @author Dmitriy Faketri
 */
public class UserResponse {

    private UUID id;

    private String email;

    private Image profileImage;

    private String login;

    private String name;

    private String surname;

    private Basket basket;

    private String city;

    private Set<ERole> role;

    private Set<Orders> orders;

    private Set<Product> favoriteProduct;

    private LocalDateTime dateOfCreate;

    private LocalDateTime dateOfBirthday;

    public UserResponse() {
    }

    public UserResponse(UUID id, String email, Image profileImage, String login, String name, String surname) {
        this.id = id;
        this.email = email;
        this.profileImage = profileImage;
        this.login = login;
        this.name = name;
        this.surname = surname;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
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

    public void setOrders(Set<Orders> orders
    ) {
        this.orders = orders;
    }

    public Set<Product> getFavoriteProduct() {
        return favoriteProduct;
    }

    public void setFavoriteProduct(Set<Product> favoriteProduct
    ) {
        this.favoriteProduct = favoriteProduct;
    }

    public LocalDateTime getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(LocalDateTime dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public LocalDateTime getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(LocalDateTime dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        UserResponse that = (UserResponse) object;
        return Objects.equals(id, that.id) && Objects.equals(email,
                that.email
        ) && Objects.equals(
                login,
                that.login
        ) && Objects.equals(city, that.city) && Objects.equals(
                role,
                that.role
        ) && Objects.equals(dateOfCreate, that.dateOfCreate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, login, city, role, dateOfCreate);
    }

}
