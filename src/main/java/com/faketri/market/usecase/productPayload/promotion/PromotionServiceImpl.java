package com.faketri.market.usecase.productPayload.promotion;

import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.productPayload.promotion.gateway.PromotionRepository;
import com.faketri.market.entity.productPayload.promotion.model.Promotion;
import com.faketri.market.infastructure.productPayload.product.gateway.ProductService;
import com.faketri.market.infastructure.productPayload.promotion.gateway.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * The type Promotion service.
 *
 * @author Dmitriy Faketri
 */
@Service
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionImpl;
    private final ProductService productService;

    @Autowired
    public PromotionServiceImpl(PromotionRepository promotionImpl, ProductService productService) {
        this.promotionImpl = promotionImpl;
        this.productService = productService;
    }

    /**
     * Find by id promotion.
     *
     * @param id the id
     * @return the promotion
     */
    public Promotion findById(UUID id) {
        return promotionImpl.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Promotion with id - " + id + " not found"));
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Promotion> findAll() {
        return promotionImpl.findAll();
    }

    /**
     * Find all page.
     *
     * @param pageable the pageable
     * @return the page
     */
    public Page<Promotion> findAll(Pageable pageable) {
        return promotionImpl.findAll(pageable);
    }

    /**
     * Is promotion active.
     *
     * @param promotion the promotion
     */
    public void isPromotionActive(Promotion promotion) {
        LocalDateTime dateNow = LocalDateTime.now();
        if (dateNow.isAfter(promotion.getDateOfStart()) && dateNow.isBefore(promotion.getDateOfEnd())) {
            System.out.println("работают");
            promotion.getPromotionProductItems().forEach(product -> product.setPromoItem(true));

        }
    }

    /**
     * Save promotion.
     *
     * @param promotion the promotion
     * @return the promotion
     */
    public Promotion save(Promotion promotion) {
        isPromotionActive(promotion);
        System.out.println(promotion.getPromotionProductItems());
        return promotionImpl.save(promotion);
    }

}
