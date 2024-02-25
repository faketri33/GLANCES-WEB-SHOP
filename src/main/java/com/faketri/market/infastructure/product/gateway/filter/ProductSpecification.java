package com.faketri.market.infastructure.product.gateway.filter;

import com.faketri.market.entity.product.model.Brand;
import com.faketri.market.entity.product.model.Categories;
import com.faketri.market.entity.product.model.Characteristics;
import com.faketri.market.entity.product.model.Product;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductSpecification {

    public Specification<Product> hasCharacteristics(
            Characteristics characteristics
    ) {
        return (root, query, criteriaBuilder) -> {
            Join<Characteristics, Product> productCharacteristics =
                    root.join("characteristics");
            return criteriaBuilder.and(criteriaBuilder.equal(
                    productCharacteristics.get("name"),
                    characteristics.getName()
            ), criteriaBuilder.equal(productCharacteristics.get("value"),
                                     characteristics.getValue()
            ));
        };
    }

    public Specification<Product> hasCharacteristics(
            List<Characteristics> characteristics
    ) {
        return characteristics.stream()
                              .map(this::hasCharacteristics)
                              .reduce(Specification::and)
                              .orElseThrow(RuntimeException::new);
    }

    public Specification<Product> hasCategories(Long categoriesId) {
        return (root, query, criteriaBuilder) -> {
            Join<Categories, Product> productCategories =
                    root.join("categories");
            return criteriaBuilder.equal(productCategories.get("id"),
                                         categoriesId
            );
        };
    }

    public Specification<Product> likeNameAndBrand(String name, String brandName
    ) {
        return (root, query, criteriaBuilder) -> {
            Join<Brand, Product> brandProductJoin = root.join("brand");

            return criteriaBuilder.or(criteriaBuilder.like(root.get("nameModel"),
                                                           name
                                      ),
                                      criteriaBuilder.like(
                                              brandProductJoin.get("brand_name"),
                                              brandName
                                      )
            );
        };
    }

    public Specification<Product> isPromoItem() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(
                "isPromoItem"), true);
    }

}
