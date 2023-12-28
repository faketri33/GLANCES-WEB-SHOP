package com.faketri.market.domain.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "public", name = "characteristics")
public class Characteristics {
    @Id
    private Long id;
    @Column
    private String name;
    @Column
    private String value;

}
