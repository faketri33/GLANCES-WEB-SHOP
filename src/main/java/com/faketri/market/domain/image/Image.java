package com.faketri.market.domain.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "public", name = "image")
public class Image {

    @Id
    private Long   id;
    @Column()
    private byte[] photo;

    public Image(File file) throws IOException {
        this.photo = Files.readAllBytes(file.toPath());
    }

}