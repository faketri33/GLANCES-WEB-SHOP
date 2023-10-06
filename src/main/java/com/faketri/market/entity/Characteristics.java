package com.faketri.market.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "characteristics")
@Table(schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Characteristics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String value;
}
