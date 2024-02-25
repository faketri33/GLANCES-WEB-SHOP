package com.faketri.market.entity.image.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/**
 * The type Image.
 *
 * @author Dmitriy Faketri
 */
@Getter
@Setter
@ToString
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column
    private String path;

    /**
     * Instantiates a new Image.
     */
    public Image() {
    }

    /**
     * Instantiates a new Image.
     *
     * @param id   the id
     * @param path the path
     */
    public Image(Long id, String path) {
        this.id = id;
        this.path = path;
    }

}