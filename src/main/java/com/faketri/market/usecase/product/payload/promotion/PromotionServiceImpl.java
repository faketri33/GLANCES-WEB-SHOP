package com.faketri.market.usecase.product.payload.promotion;

import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.product.payload.promotion.exception.ProductAlreadyParticipatesInPromoException;
import com.faketri.market.entity.product.payload.promotion.gateway.PromotionRepository;
import com.faketri.market.entity.product.payload.promotion.model.Promotion;
import com.faketri.market.infastructure.product.payload.product.gateway.ProductService;
import com.faketri.market.infastructure.product.payload.promotion.gateway.PromotionService;
import com.faketri.market.usecase.file.FileUploadService;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    private final ProductService productService;
    private final FileUploadService fileService;

    @Autowired
    public PromotionServiceImpl(PromotionRepository promotionImpl, ProductService productService, FileUploadService fileService) {
        this.promotionImpl = promotionImpl;
        this.productService = productService;
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

    @Override
    public Page<Promotion> likeByName(String name, Pageable pageable) {
        return promotionImpl.findByTitleLike('%' + name.toLowerCase().trim() + '%', pageable);
    }

    public void isPromotionActive(Promotion promotion) {
        LocalDate dateNow = LocalDate.now();
        if ((dateNow.isAfter(promotion.getDateOfStart()) || dateNow.equals(promotion.getDateOfStart()))
                && dateNow.isBefore(promotion.getDateOfEnd())) {
            promotion.getPromotionProductItems().forEach(product -> {
                boolean isPromotionActiveExists = promotionImpl
                        .findActivePromotionsByProductId(product.getId(), LocalDate.now(), promotion.getId());
                log.info("isPromotionActiveExists: " + isPromotionActiveExists);
                if (isPromotionActiveExists)
                    throw new ProductAlreadyParticipatesInPromoException("Продукт уже участвует в акции.");

                product.setPromoItem(true);
            });
        }
        else {
            promotion.getPromotionProductItems().forEach(product -> product.setPromoItem(false));
            productService.save(promotion.getPromotionProductItems());
        }
    }

    @Override
    public Promotion create(Promotion promotion, MultipartFile multipartFiles) {
        if (multipartFiles != null && !multipartFiles.isEmpty())
            promotion.setBanner(fileService.saveImage(FileUploadService.PROMOTION_PATH, promotion.getTitle(), multipartFiles));
        return save(promotion);
    }

    public Promotion update(Promotion promotion, MultipartFile multipartFiles) {
        if (multipartFiles != null && !multipartFiles.isEmpty())
            promotion.setBanner(fileService.saveImage(FileUploadService.PROMOTION_PATH, promotion.getTitle(), multipartFiles));

        final Promotion promotionInDateBase = findById(promotion.getId());

        promotionInDateBase.getPromotionProductItems().removeAll(promotion.getPromotionProductItems());
        promotionInDateBase.getPromotionProductItems().forEach(p -> p.setPromoItem(false));
        productService.save(promotionInDateBase.getPromotionProductItems());

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
