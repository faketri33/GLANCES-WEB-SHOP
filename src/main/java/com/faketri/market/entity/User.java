package com.faketri.market.entity;

import com.faketri.market.entity.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{
        private long id;
        private String email;
        private String login;
        private String password;
        private String city;
        private Set<ERole> role = new HashSet<>();
        private Set<Order> order = new HashSet<>();
        private Set<Product> favoriteProduct = new HashSet<>();
}
