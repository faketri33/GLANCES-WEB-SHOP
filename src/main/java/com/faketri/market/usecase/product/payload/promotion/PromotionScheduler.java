package com.faketri.market.usecase.product.payload.promotion;

import com.faketri.market.entity.product.payload.product.model.Product;
import com.faketri.market.entity.product.payload.promotion.model.Promotion;
import com.faketri.market.infastructure.product.payload.product.gateway.ProductService;
import com.faketri.market.infastructure.product.payload.promotion.gateway.PromotionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class PromotionScheduler {
    static final Logger log = LoggerFactory.getLogger(PromotionScheduler.class);
    private final PromotionService promotionService;
    private final ProductService productService;
    private List<Promotion> promotions;

    public PromotionScheduler(PromotionService promotionService, ProductService productService) {
        this.promotionService = promotionService;
        this.productService = productService;
    }

    @Scheduled(cron = "0 0 0 * * ?") // Запуск каждый день в полночь
    public void checkPromotions() {
        log.info("checkPromotions: start task");
        this.promotions = this.promotionService.findAll();
        checkExpiredPromotions();
        checkStartedPromotions();
    }

    private void checkStartedPromotions() {
        log.info("checkStartedPromotions: validation date");
        promotions.stream()
                .filter(p ->
                        p.getDateOfStart().isBefore(LocalDate.now())
                        && p.getDateOfEnd().isAfter(LocalDate.now())
                )
                .forEach(this::activatePromotion);
    }

    private void activatePromotion(Promotion promotion) {
        List<Product> promotionProducts = promotion.getPromotionProductItems();
        promotionProducts.forEach(this::updateProductWithPromotion);
    }

    private void updateProductWithPromotion(Product product) {
        log.info("updateProductWithPromotion: product id -" + product.getId());
        product.setPromoItem(true);
        productService.save(product);
    }

    public void checkExpiredPromotions() {
        promotions.stream()
                .filter(p -> p.getDateOfEnd().isBefore(LocalDate.now()))
                .forEach(this::expirePromotion);
    }

    private void expirePromotion(Promotion promotion) {
        List<Product> promotionProducts = promotion.getPromotionProductItems();
        promotionProducts.forEach(this::removeProductFromPromotion);
    }

    private void removeProductFromPromotion(Product promotionProduct) {
        log.info("removeProductFromPromotion: product id -" + promotionProduct.getId());
        promotionProduct.setDiscount((short) 0);
        promotionProduct.setPromoItem(false);
        productService.save(promotionProduct);
    }
}
