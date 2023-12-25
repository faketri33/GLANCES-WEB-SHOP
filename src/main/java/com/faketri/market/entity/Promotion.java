package com.faketri.market.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "public")
public class Promotion {
    @Id
    private Long id;
    @Column
    private byte[] banner;
    @Column
    private String title;
    @Column
    private String discription;
    @Column
    private LocalDateTime dateOfStart;
    @Column
    private LocalDateTime dateOfEnd;
    private Set<PromotionItem> products = new HashSet<>();

    public Promotion(Long id, byte[] banner, String title, String discription,
                     LocalDateTime dateOfStart, LocalDateTime dateOfEnd) {
        this.id = id;
        this.banner = banner;
        this.title = title;
        this.discription = discription;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Promotion promotion = (Promotion) o;
        return Objects.equals(id, promotion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
