package com.faketri.market.entity.productPayload.promotion.model;

import com.faketri.market.entity.image.model.Image;
import com.faketri.market.entity.productPayload.product.model.Product;
import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * The type Promotion.
 *
 * @author Dmitriy Faketri
 */
@Entity
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    private Image banner;

    @Column
    private String title;

    @Column(length = 3000)
    private String description;

    @Column
    private LocalDateTime dateOfStart;

    @Column
    private LocalDateTime dateOfEnd;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
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
    public Promotion(UUID id, Image banner, String title, String description,
                     LocalDateTime dateOfStart, LocalDateTime dateOfEnd
    ) {
        this.id = id;
        this.banner = banner;
        this.title = title;
        this.description = description;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Image getBanner() {
        return banner;
    }

    public void setBanner(Image banner) {
        this.banner = banner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(LocalDateTime dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public LocalDateTime getDateOfEnd() {
        return dateOfEnd;
    }

    public void setDateOfEnd(LocalDateTime dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
    }

    public List<Product> getPromotionProductItems() {
        return promotionProductItems;
    }

    public void setPromotionProductItems(List<Product> promotionProductItems) {
        this.promotionProductItems = promotionProductItems;
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
        Promotion promotion = (Promotion) o;
        return getId() != null && Objects.equals(getId(), promotion.getId());
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
        return "Promotion{" +
                "id=" + id +
                ", banner=" + banner +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dateOfStart=" + dateOfStart +
                ", dateOfEnd=" + dateOfEnd +
                '}';
    }
}
