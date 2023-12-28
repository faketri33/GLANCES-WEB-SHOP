package com.faketri.market.domain.order;

import com.faketri.market.domain.product.Product;
import com.faketri.market.domain.users.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "public", name = "rating")
public class Rating {
    @Id
    private Long id;
    @Column
    private String description;
    @Column
    private short grade;
    @Column
    private Product product;
    @Column
    private User user;
    @Column
    private LocalDateTime publishedOn;

    public Rating(Long id, String description, short grade, Product product, User user) {
        this.id = id;
        this.description = description;
        this.grade = grade;
        this.product = product;
        this.user = user;
    }
    private void onCreate(){
        publishedOn = LocalDateTime.now();
    }

}
