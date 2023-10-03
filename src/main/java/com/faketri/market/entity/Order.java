package com.faketri.market.entity;

import com.faketri.market.entity.enums.EStatusOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private Set<Product> products = new HashSet<>();
    @Column
    private Date dateOfCreate;
    @Column
    private Date dateOfRelease;
    @Column
    private double price;
    @Enumerated(EnumType.STRING)
    private EStatusOrder statusOrder;
}
