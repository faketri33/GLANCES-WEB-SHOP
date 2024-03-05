package com.faketri.market.infastructure.product.gateway.filter;

import com.faketri.market.entity.product.model.Product;
import com.faketri.market.entity.product.model.child.Characteristics;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ProductSpecification {

    Specification<Product> hasCharacteristics(Characteristics characteristics);

    Specification<Product> hasCharacteristics(List<Characteristics> characteristics);

    Specification<Product> hasCategories(Long categoriesId);

    Specification<Product> likeNameAndBrand(String name, String brandName);

    Specification<Product> isPromoItem();

}
