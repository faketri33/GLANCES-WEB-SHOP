package com.faketri.market.usecase.file;

import com.faketri.market.entity.exception.ImageFormatException;
import com.faketri.market.entity.image.model.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class FileUploadService {

    static final Logger log = LoggerFactory.getLogger(FileUploadService.class);
    private static final List<String> ALLOWED_EXTENSIONS = List.of("jpg", "jpeg", "png");
    public static final String PRODUCT_PATH = "/app/images/product/";
    public static final String CATEGORIES_PATH = "/app/images/categories/";
    public static final String PROMOTION_PATH = "/app/images/promotion/";
    public static final String USER_PATH = "/app/images/user/profile/";

    public boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && ALLOWED_EXTENSIONS.contains(contentType);
    }

    public List<Image> saveImages(String path, String name, List<MultipartFile> images){
            AtomicInteger iterator = new AtomicInteger(0);
            return images.stream()
                    .map(img -> saveImage(path, name + iterator.getAndIncrement(), img))
                    .collect(Collectors.toList());
    }

    public Image saveImage(String path, String name, MultipartFile image){
        if (image == null) return null;

        if (!isImageFile(image))
            throw new ImageFormatException("Не подходящий формат изображения.");

        final String imageName = path + name.replace(' ', '-') + "-" + image.getOriginalFilename();

        try {
            image.transferTo(Paths.get(imageName));
        } catch (IOException e) {
            log.error(this.getClass() + " " + e.getMessage());
        }

        return new Image(null, imageName);
    }
}
