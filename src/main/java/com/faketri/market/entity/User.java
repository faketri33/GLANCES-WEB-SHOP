package com.faketri.market.entity;

import com.faketri.market.entity.enums.ECategories;
import com.faketri.market.entity.enums.ERole;
import com.faketri.market.entity.enums.EStatusOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "user")
@Table(schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        @Column(nullable = false)
        private String email;
        @Column(nullable = false, unique = true)
        private String login;
        @Column(nullable = false, length = 3000)
        private String password;
        @Column
        private String city;
        @ElementCollection(targetClass = ERole.class)
        @CollectionTable(name = "user_role",
                joinColumns = @JoinColumn(name = "user_id"))
        private Set<ERole> role = new HashSet<>();
        @OneToMany
        private Set<Order> orders = new HashSet<>();


}
