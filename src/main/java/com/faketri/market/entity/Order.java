package com.faketri.market.entity;

import com.faketri.market.entity.enums.EStatusOrder;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity(name = "order")
@Table(schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToMany
    @JoinColumn(name="product_id")
    private List<Product> products = new ArrayList<>();
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime dateOfCreate;
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column
    private LocalDateTime dateOfRelease;
    @Column
    private double price;
    @Enumerated(EnumType.STRING)
    private EStatusOrder statusOrder;

    @PrePersist
    protected void onCreate() {
        this.dateOfCreate = LocalDateTime.now();
    }
}
