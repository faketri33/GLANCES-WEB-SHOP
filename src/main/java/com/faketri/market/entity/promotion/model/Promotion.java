package com.faketri.market.entity.promotion.model;

import com.faketri.market.entity.image.model.Image;
import com.faketri.market.entity.product.model.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Promotion.
 *
 * @author Dmitriy Faketri
 */
@Getter
@Setter
@ToString
@Entity
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @OneToOne
    private Image banner;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private LocalDateTime dateOfStart;

    @Column
    private LocalDateTime dateOfEnd;

    @OneToMany()
    @ToString.Exclude
    private List<Product> promotionProductItems = new ArrayList<>();

    /**
     * Instantiates a new Promotion.
     */
    public Promotion() {
    }

    /**
     * Instantiates a new Promotion.
     *
     * @param id          the id
     * @param banner      the banner
     * @param title       the title
     * @param description the description
     * @param dateOfStart the date of start
     * @param dateOfEnd   the date of end
     */
    public Promotion(Long id, Image banner, String title, String description,
                     LocalDateTime dateOfStart, LocalDateTime dateOfEnd
    ) {
        this.id = id;
        this.banner = banner;
        this.title = title;
        this.description = description;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
    }

}
