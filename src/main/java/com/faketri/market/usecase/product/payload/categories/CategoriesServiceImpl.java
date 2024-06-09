package com.faketri.market.usecase.product.payload.categories;

import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.product.payload.categories.gateway.CategoriesRepository;
import com.faketri.market.entity.product.payload.categories.model.Categories;
import com.faketri.market.infastructure.product.payload.categories.dto.CategoriesRequest;
import com.faketri.market.infastructure.product.payload.categories.gateway.CategoriesService;
import com.faketri.market.usecase.file.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

/**
 * The type Categories service.
 *
 * @author Dmitriy Faketri
 */
@Service
public class CategoriesServiceImpl implements CategoriesService {

    private final CategoriesRepository categoriesRepository;
    private final FileUploadService fileUploadService;

    @Autowired
    public CategoriesServiceImpl(CategoriesRepository categoriesRepository, FileUploadService fileUploadService) {
        this.categoriesRepository = categoriesRepository;
        this.fileUploadService = fileUploadService;
    }

    public Categories findById(UUID id) {
        return categoriesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Categories with id - " + id + " not found"));
    }

    public List<Categories> findAll() {
        return categoriesRepository.findAll();
    }

    public Page<Categories> findAll(Pageable pageable
    ) {
        return categoriesRepository.findAll(pageable);
    }

    @Override
    public Page<Categories> findByName(String name, Pageable pageable) {
        return categoriesRepository.findByNameLike('%' + name.toLowerCase().trim() + "%", pageable);
    }

    @Override
    public Categories create(CategoriesRequest categoriesRequest, MultipartFile images) {
        Categories categories = new Categories();
        categories.setName(categoriesRequest.getName());
        if (images != null)
            categories.setImage(
                    fileUploadService.saveImage(FileUploadService.CATEGORIES_PATH, categoriesRequest.getName(), images)
            );

        return save(categories);
    }

    public Categories save(Categories categories) {
        return categoriesRepository.findByName(categories.getName())
                .orElseGet(() -> categoriesRepository.save(categories));
    }

    public void delete(Categories categories) {
        categoriesRepository.delete(categories);
    }

    @Override
    public void deleteById(UUID id) {
        categoriesRepository.deleteById(id);
    }

    @Override
    public Categories update(Categories categoriesRequest, MultipartFile images) {
        if(images != null)
            categoriesRequest.setImage(fileUploadService.saveImage(FileUploadService.CATEGORIES_PATH, categoriesRequest.getName(), images));
        return categoriesRepository.save(categoriesRequest);
    }
}
