package com.faketri.market.domain.order;

import com.faketri.market.domain.product.Product;
import com.faketri.market.domain.users.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "public", name = "order")
public class Order {

    @Id
    private long          id;
    @Column
    private User          user;
    @MappedCollection
    private List<Product> products = new ArrayList<>();
    @Column
    private LocalDateTime dateOfCreate;
    @Column
    private LocalDateTime dateOfRelease;
    @Column
    private double        price;
    @Column
    private EStatusOrder  statusOrder;

    private void onCreate() {
        dateOfCreate = LocalDateTime.now();
    }

}
