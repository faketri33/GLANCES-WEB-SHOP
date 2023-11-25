package com.faketri.market.repository;

import com.faketri.market.entity.Brand;
import com.faketri.market.entity.Categories;
import com.faketri.market.entity.Characteristics;
import com.faketri.market.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandDao extends CrudRepository<Brand, Long> {
    interface ProductDao {

        List<Product> findByCategories(Categories categories);
        List<Product> findByCharacteristics(Characteristics characteristics);
    }
}
