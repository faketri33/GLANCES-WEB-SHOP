package com.faketri.market.domain.product;

import com.faketri.market.domain.image.Image;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long                 id;
    @ManyToOne
    private Brand                brand;
    @Column
    private String               nameModel;
    @ManyToOne
    private Categories           categories;
    @OneToMany
    @ToString.Exclude
    private Set<Image>           image           = new HashSet<>();
    @ManyToMany
    @ToString.Exclude
    private Set<Characteristics> characteristics = new HashSet<>();
    @Column
    private Long                 price;
    @Column
    private Long                 promoPrice;
    @Column
    private Boolean              isPromoItem;
    @Column
    private Short                discount;
    @Min(0)
    @Column(nullable = false)
    private int                  quantity;
    @Column
    private int                  quantitySold    = 0;

    public Product() {
    }

    public Product(Long id, Brand brand, String nameModel,
                   Categories categories, Long price, int quantity,
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
        Product product = (Product) o;
        return getId() != null && Objects.equals(getId(), product.getId());
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
