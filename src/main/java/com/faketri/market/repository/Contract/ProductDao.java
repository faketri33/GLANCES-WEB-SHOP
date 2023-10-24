package com.faketri.market.repository.Contract;

import com.faketri.market.entity.Categories;
import com.faketri.market.entity.Characteristics;
import com.faketri.market.entity.Product;

import java.util.List;

public interface ProductDao {

    List<Product> findByCategories(Categories categories);
    List<Product> findByCharacteristics(Characteristics characteristics);
}
