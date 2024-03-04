package com.faketri.market.usecase.product.filter;

import com.faketri.market.entity.product.model.Product;
import com.faketri.market.entity.product.model.child.Brand;
import com.faketri.market.entity.product.model.child.Categories;
import com.faketri.market.entity.product.model.child.Characteristics;
import com.faketri.market.infastructure.product.gateway.filter.ProductSpecification;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductSpecificationImpl implements ProductSpecification {

    public Specification<Product> hasCharacteristics(
            Characteristics characteristics
    ) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join(
                                                                               "characteristics").get("id"),
                                                                       characteristics.getId()
        );
    }

    public Specification<Product> hasCharacteristics(
            List<Characteristics> characteristics
    ) {
        return (root, query, criteriaBuilder) -> root.join("characteristics")
                                                     .get("id")
                                                     .in(characteristics.stream()
                                                                        .map(Characteristics::getId)
                                                                        .collect(
                                                                                Collectors.toList()));
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

            return criteriaBuilder.and(criteriaBuilder.like(root.get("nameModel"),
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
