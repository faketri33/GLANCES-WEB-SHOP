package com.faketri.market.infastructure.product.payload.product.gateway.filter;

import com.faketri.market.entity.product.payload.characteristics.model.Characteristics;
import com.faketri.market.entity.product.payload.product.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface ProductSpecification {

    Specification<Product> hasCharacteristics(Characteristics characteristics);

    Specification<Product> hasCharacteristics(List<Characteristics> characteristics);

    Specification<Product> hasCharacteristicsByUUID(List<UUID> characteristics);

    Specification<Product> hasCategories(UUID categoriesId);

    Specification<Product> likeByNameModelOrBrandName(String name);

    Specification<Product> isPromoItem();

    Specification<Product> priceBetween(Integer minPrice, Integer maxPrice);

}
