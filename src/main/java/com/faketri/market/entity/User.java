package com.faketri.market.entity;

import com.faketri.market.entity.enums.ERole;
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
@Table(schema = "public")
public class User{
        @Id
        private long id;
        @Column
        private String email;
        @Column
        private String login;
        @Column
        private String password;
        @Column
        private String city;
        @MappedCollection
        private Set<ERole> role = new HashSet<>();
        @MappedCollection
        private Set<Order> order = new HashSet<>();
        @MappedCollection
        private Set<Product> favoriteProduct = new HashSet<>();

        public User(long id, String login) {
                this.id = id;
                this.login = login;
        }
}
