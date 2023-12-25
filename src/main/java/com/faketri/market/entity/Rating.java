package com.faketri.market.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Reader;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "public")
public class Rating {
    @Id
    private Long id;
    @Column
    private String description;
    @Column
    private short grade;
    @Column
    private Long product_id;
    @Column
    private Long user_id;
    @Column
    private LocalDateTime publishedOn;

    public Rating(Long id, String description, short grade, Long product_id, Long user) {
        this.id = id;
        this.description = description;
        this.grade = grade;
        this.product_id = product_id;
        this.user_id = user;
    }

    private void onCreate(){
        publishedOn = LocalDateTime.now();
    }

}
