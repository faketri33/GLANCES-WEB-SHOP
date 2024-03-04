package com.faketri.market.usecase.promotion;

import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.promotion.gateway.PromotionRepository;
import com.faketri.market.entity.promotion.model.Promotion;
import com.faketri.market.infastructure.promotion.gateway.PromotionService;
import com.faketri.market.usecase.product.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Promotion service.
 *
 * @author Dmitriy Faketri
 */
@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionRepository promotionImpl;
    @Autowired
    private ProductServiceImpl  productService;

    /**
     * Find by id promotion.
     *
     * @param id the id
     *
     * @return the promotion
     */
    public Promotion findById(Long id) {
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
     *
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
    }

    /**
     * Save promotion.
     *
     * @param promotion the promotion
     *
     * @return the promotion
     */
    public Promotion save(Promotion promotion) {
        isPromotionActive(promotion);
        return promotionImpl.save(promotion);
    }

}
