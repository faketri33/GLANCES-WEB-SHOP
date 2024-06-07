package com.faketri.market.usecase.product.payload.promotion;

import com.faketri.market.entity.exception.ImageFormatException;
import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.image.model.Image;
import com.faketri.market.entity.product.payload.promotion.gateway.PromotionRepository;
import com.faketri.market.entity.product.payload.promotion.model.Promotion;
import com.faketri.market.infastructure.product.payload.product.dto.ProductCreateRequest;
import com.faketri.market.infastructure.product.payload.product.gateway.ProductService;
import com.faketri.market.infastructure.product.payload.promotion.gateway.PromotionService;
import com.faketri.market.usecase.file.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * The type Promotion service.
 *
 * @author Dmitriy Faketri
 */
@Service
public class PromotionServiceImpl implements PromotionService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final PromotionRepository promotionImpl;
    private final FileService fileService;

    @Autowired
    public PromotionServiceImpl(PromotionRepository promotionImpl, FileService fileService) {
        this.promotionImpl = promotionImpl;
        this.fileService = fileService;
    }

    public Promotion findById(UUID id) {
        return promotionImpl.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Promotion with id - " + id + " not found"));
    }

    public List<Promotion> findAll() {
        return promotionImpl.findAll();
    }

    public Page<Promotion> findAll(Pageable pageable) {
        return promotionImpl.findAll(pageable);
    }

    public void isPromotionActive(Promotion promotion) {
        LocalDate dateNow = LocalDate.now();
        if (dateNow.isAfter(promotion.getDateOfStart()) && dateNow.isBefore(promotion.getDateOfEnd()))
            promotion.getPromotionProductItems().forEach(product -> product.setPromoItem(true));
    }

    @Override
    public Promotion create(Promotion promotion, MultipartFile multipartFiles) {
        if(! fileService.isImageFile(multipartFiles)) throw new ImageFormatException("Неверный формат изображения.");

        final String path = "/app/images/promo/";
        final String imageName = path + promotion.getTitle().replace(' ', '-') + "-" + multipartFiles.getOriginalFilename();

        try {
            multipartFiles.transferTo(Paths.get(imageName));
        } catch (IOException e) {
            log.error(this.getClass() + " " + e.getMessage());
        }

        promotion.setBanner(new Image(null, imageName));


        return save(promotion);
    }

    public Promotion save(Promotion promotion) {
        isPromotionActive(promotion);
        return promotionImpl.save(promotion);
    }

    @Override
    public void deleteById(UUID id) {
        promotionImpl.deleteById(id);
    }

}
