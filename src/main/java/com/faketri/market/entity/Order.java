package com.faketri.market.entity;

import com.faketri.market.entity.enums.EStatusOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private long id;
    private User user;
    private List<Product> products = new ArrayList<>();
    private LocalDateTime dateOfCreate;
    private LocalDateTime dateOfRelease;
    private double price;
    private EStatusOrder statusOrder;
}
