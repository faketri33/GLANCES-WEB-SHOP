package com.faketri.market.domain.product;

import com.faketri.market.domain.image.Image;
import com.faketri.market.domain.order.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "public", name = "product")
public class Product {

    @Id
    private Long                 id;
    private Brand                brand;
    private String               nameModel;
    private Categories           categories;
    @MappedCollection
    private Set<Image>           image           = new HashSet<>();
    @MappedCollection
    private Set<Rating>          rating          = new HashSet<>();
    @MappedCollection
    private Set<Characteristics> characteristics = new HashSet<>();
    private Long                 price;
    private Boolean              isPromoActive   = false;
    private Long                 promoPrice;
    private Integer              discount        = 0;
    private int                  quantity;
    private int                  quantitySold    = 0;

    public Product(Long id, Brand brand, String nameModel,
                   Categories categories, Long price, Boolean isPromoActive,
                   Long promoPrice, Integer discount, int quantity,
                   int quantitySold
    ) {
        this.id = id;
        this.brand = brand;
        this.nameModel = nameModel;
        this.categories = categories;
        this.price = price;
        this.isPromoActive = isPromoActive;
        this.promoPrice = promoPrice;
        this.discount = discount;
        this.quantity = quantity;
        this.quantitySold = quantitySold;
    }

    public void updatePriceDiscount(char operation) {
        switch (operation) {
            case '+':
                promoPrice = 0L;
                break;
            case '-':
                promoPrice = price - ( ( price * discount ) / 100 );
                break;
        }
    }

    public void setIsPromoActive(boolean isActive) {
        isPromoActive = isActive;
        if (isPromoActive) updatePriceDiscount('-');
        else updatePriceDiscount('+');
    }

}
