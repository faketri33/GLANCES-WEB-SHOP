package com.faketri.market.usecase.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileService {

    private static final List<String> ALLOWED_EXTENSIONS = List.of("jpg", "jpeg", "png");

    public boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && ALLOWED_EXTENSIONS.contains(contentType);
    }
}
