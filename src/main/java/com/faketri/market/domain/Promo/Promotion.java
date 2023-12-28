package com.faketri.market.domain.Promo;

import com.faketri.market.domain.product.Product;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "public", name = "promotion")
public class Promotion {
    @Id
    private Long id;
    @Column
    private byte[] banner;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private LocalDateTime dateOfStart;
    @Column
    private LocalDateTime dateOfEnd;
    @MappedCollection
    private Set<Product> promotionItems = new HashSet<>();

    public Promotion(Long id, byte[] banner, String title, String description,
                     LocalDateTime dateOfStart, LocalDateTime dateOfEnd) {
        this.id = id;
        this.banner = banner;
        this.title = title;
        this.description = description;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
    }
}
