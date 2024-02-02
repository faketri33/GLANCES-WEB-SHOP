package com.faketri.market.domain.users;

import com.faketri.market.domain.order.Order;
import com.faketri.market.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "public", name = "user")
public class User {

    @Id
    private Long         id;
    @Column
    private String       email;
    @Column
    private String       login;
    @Column
    private String       password;
    @Column
    private String       city;
    @MappedCollection
    private Set<ERole>   role            = new HashSet<>();
    @MappedCollection
    private Set<Order>   order           = new HashSet<>();
    @MappedCollection
    private Set<Product> favoriteProduct = new HashSet<>();

}
