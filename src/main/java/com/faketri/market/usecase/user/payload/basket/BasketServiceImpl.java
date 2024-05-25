package com.faketri.market.usecase.user.payload.basket;

import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.product.payload.product.model.Product;
import com.faketri.market.entity.product.payload.product.model.ProductItem;
import com.faketri.market.entity.user.payload.basket.gateway.BasketRepository;
import com.faketri.market.entity.user.payload.basket.model.Basket;
import com.faketri.market.infastructure.product.payload.product.gateway.ProductService;
import com.faketri.market.infastructure.user.payload.basket.gateway.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;

    @Autowired
    public BasketServiceImpl(BasketRepository basketRepository, ProductService productService) {
        this.basketRepository = basketRepository;
        this.productService = productService;
    }

    @Override
    public Basket findById(UUID uuid) {
        return basketRepository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("Basket not found"));
    }

    @Override
    public Basket save(Basket basket) {
        basket.updatePrice();
        return basketRepository.save(basket);
    }

    @Override
    public Basket addProductToBasket(final UUID basketId, final UUID productId, final int quantity) {
        final Basket basket = findById(basketId);

        for (ProductItem item : basket.getProducts())
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity);
                return save(basket);
            }

        final Product product = productService.findById(productId);
        final ProductItem newItem = new ProductItem();
        newItem.setProduct(product);
        newItem.setQuantity(quantity);
        basket.getProducts().add(newItem);

        return save(basket);
    }

    @Override
    public void removeFromBasket(UUID basketId, UUID productId, int quantity) {
        final Basket basket = findById(basketId);
        for (ProductItem item : basket.getProducts())
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(item.getQuantity() - quantity);
                System.out.println("QUANTITY " +item.getQuantity());
                if (item.getQuantity() == 0) basket.getProducts().remove(item);
                save(basket);
                return;
            }
    }

}
