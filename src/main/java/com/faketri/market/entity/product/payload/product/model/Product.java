package com.faketri.market.entity.product.payload.product.model;

import com.faketri.market.entity.image.model.Image;
import com.faketri.market.entity.product.payload.brand.model.Brand;
import com.faketri.market.entity.product.payload.categories.model.Categories;
import com.faketri.market.entity.product.payload.characteristics.model.Characteristics;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import org.hibernate.proxy.HibernateProxy;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * The type Product.
 *
 * @author Dmitriy Faketri
 */
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
    @ManyToOne
    private Brand brand;
    @Column
    private String nameModel;
    @ManyToOne
    private Categories categories;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Image> image = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Characteristics> characteristics = new HashSet<>();
    @Column
    private Integer price;
    @Column
    private Integer promoPrice = 0;
    @Column
    private Boolean isPromoItem = false;
    @Column
    private Short discount = 0;
    @Min(0)
    @Column(nullable = false)
    private int quantity;
    @Column
    private int quantitySold = 0;

    /**
     * Instantiates a new Product.
     */
    public Product() {
    }

    /**
     * Instantiates a new Product.
     *
     * @param id           the id
     * @param brand        the brand
     * @param nameModel    the name model
     * @param categories   the categories
     * @param price        the price
     * @param quantity     the quantity
     * @param quantitySold the quantity sold
     */
    public Product(UUID id, Brand brand, String nameModel,
                   Categories categories, Integer price, int quantity,
                   int quantitySold
    ) {
        this.id = id;
        this.brand = brand;
        this.nameModel = nameModel;
        this.categories = categories;
        this.price = price;
        this.quantity = quantity;
        this.quantitySold = quantitySold;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getNameModel() {
        return nameModel;
    }

    public void setNameModel(String nameModel) {
        this.nameModel = nameModel;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Set<Image> getImage() {
        return image;
    }

    public void setImage(Set<Image> image) {
        this.image = image;
    }

    public Set<Characteristics> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Set<Characteristics> characteristics) {
        this.characteristics = characteristics;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(Integer promoPrice) {
        this.promoPrice = promoPrice;
    }

    public Boolean isPromoItem() {
        return isPromoItem;
    }

    public void setPromoItem(Boolean promoItem) {
        isPromoItem = promoItem;
    }

    public Short getDiscount() {
        return discount;
    }

    public void setDiscount(Short discount) {
        if (discount > 0 && discount < 99)
            promoPrice = price - (price / 100) * discount;
        else promoPrice = price;

        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
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
        Product product = (Product) o;
        return getId() != null && Objects.equals(getId(), product.getId());
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
        return "Product{" +
                "id=" + id +
                ", brand=" + brand +
                ", nameModel='" + nameModel + '\'' +
                ", categories=" + categories +
                ", price=" + price +
                ", promoPrice=" + promoPrice +
                ", isPromoItem=" + isPromoItem +
                ", discount=" + discount +
                ", quantity=" + quantity +
                ", quantitySold=" + quantitySold +
                '}';
    }
}
