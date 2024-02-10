package com.faketri.market.service.promo;

import com.faketri.market.domain.promo.Promotion;
import com.faketri.market.payload.response.exception.ResourceNotFoundException;
import com.faketri.market.repository.PromotionRepository;
import com.faketri.market.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionImpl;
    @Autowired
    private ProductService      productService;

    public Promotion findById(Long id) {
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
    }

    public Promotion save(Promotion promotion) {
        isPromotionActive(promotion);
        return promotionImpl.save(promotion);
    }

    /*public Boolean update(Promotion promotion) {
        return promotionImpl.update(promotion);
    }*/

}
