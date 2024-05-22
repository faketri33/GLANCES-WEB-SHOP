package com.faketri.market.usecase.product.payload.product.filter;

import com.faketri.market.entity.product.payload.brand.model.Brand;
import com.faketri.market.entity.product.payload.categories.model.Categories;
import com.faketri.market.entity.product.payload.characteristics.model.Characteristics;
import com.faketri.market.entity.product.payload.product.model.Product;
import com.faketri.market.infastructure.product.payload.product.gateway.filter.ProductSpecification;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ProductSpecificationImpl implements ProductSpecification {

    public Specification<Product> hasCharacteristics(
            Characteristics characteristics
    ) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                    root.join("characteristics").get("id"), characteristics.getId()
                );
    }

    public Specification<Product> hasCharacteristics(
            UUID characteristics
    ) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.join("characteristics").get("id"), characteristics
                );
    }

    public Specification<Product> hasCharacteristics(
            List<Characteristics> characteristics
    ) {
        return (root, query, criteriaBuilder) ->
                root.join("characteristics")
                    .get("id")
                    .in(characteristics.stream()
                    .map(Characteristics::getId)
                    .toList());
    }

    public Specification<Product> hasCharacteristicsByUUID(
            List<UUID> characteristics
    ) {
        return (root, query, criteriaBuilder) ->
                root.join("characteristics")
                        .get("id")
                        .in(characteristics.stream()
                        .toList());
    }

    public Specification<Product> hasCategories(UUID categoriesId) {
        return (root, query, criteriaBuilder) -> {
            Join<Categories, Product> productCategories =
                    root.join("categories");
            return criteriaBuilder.equal(productCategories.get("id"),
                    categoriesId
            );
        };
    }

    public Specification<Product> likeByNameModelOrBrandName(String name) {
        return (root, query, criteriaBuilder) -> {
            Join<Brand, Product> brandProductJoin = root.join("brand");
            final String likeName = "%" + name + "%";
            return criteriaBuilder.or(criteriaBuilder.like(root.get("nameModel"), likeName),
                    criteriaBuilder.like(brandProductJoin.get("name"), likeName)
            );
        };
    }

    public Specification<Product> isPromoItem() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isPromoItem"), true);
    }

}
