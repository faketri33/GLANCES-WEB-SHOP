package com.faketri.market.service;

import com.faketri.market.domain.promo.Promotion;
import com.faketri.market.payload.response.exception.ResourceNotFoundException;
import com.faketri.market.repository.impl.PromotionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.StyledEditorKit;
import java.util.List;

@Service
public class PromotionService {
    @Autowired
    private PromotionImpl promotionImpl;

    public Promotion findById(Long id){
        return promotionImpl.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Promotion with id - " + id + " not found"));
    }

    public List<Promotion> findAll(){
        return promotionImpl.findAll();
    }

    public Page<Promotion> findAll(Pageable pageable){
        return promotionImpl.findAll(pageable);
    }

    public Promotion save(Promotion promotion){
        Promotion ifSavedPromotion = promotionImpl.findByFields(promotion);
        return ifSavedPromotion == null ? promotionImpl.save(promotion) : ifSavedPromotion;
    }

    public Boolean update(Promotion promotion){
        return promotionImpl.update(promotion);
    }
}
