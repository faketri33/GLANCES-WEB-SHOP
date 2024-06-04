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

import java.util.Optional;
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
    public Basket addProductToBasket(final UUID basketId, final UUID productId) {
        final Basket basket = findById(basketId);

        final boolean productNotExists = basket.getProducts()
                .stream()
                .map(p -> p.getProduct().getId())
                .anyMatch(id -> id.equals(productId));

        if(productNotExists)
            return basket;

        final Product product = productService.findById(productId);
        final ProductItem newItem = new ProductItem();
        newItem.setProduct(product);
        newItem.setQuantity(1);
        basket.getProducts().add(newItem);

        return save(basket);
    }

    @Override
    public Basket updateQuantity(final UUID basketId, final UUID productItemId, final Integer quantity) {
        Basket basket = findById(basketId);

        if (basket == null) throw new RuntimeException("Коризна не найдена.");

        Optional<ProductItem> productItems = basket.getProducts()
                .stream()
                .filter(productItem -> productItem.getId().equals(productItemId))
                .findFirst();

        if(productItems.isEmpty()) throw new RuntimeException("Продукт не найден.");

        if(quantity < 0) throw new RuntimeException("Количество не может быть меньше нуля.");

        productItems.get().setQuantity(quantity);

        basket.updatePrice();

        return basketRepository.save(basket);
    }

    @Override
    public void removeFromBasket(UUID basketId, UUID productId) {
        final Basket basket = findById(basketId);
        ProductItem productItemToRemove = null;
        for (ProductItem item : basket.getProducts())
            if (item.getProduct().getId().equals(productId)) {
                productItemToRemove = item;
                break;
            }
        if(productItemToRemove != null)
            basket.getProducts().remove(productItemToRemove);

        save(basket);
    }

}
