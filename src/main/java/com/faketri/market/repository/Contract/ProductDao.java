package com.faketri.market.repository.Contract;

import com.faketri.market.entity.Product;
import com.faketri.market.entity.enums.ECategories;

import java.util.List;

public interface ProductDao {

    List<Product> findByCategories(ECategories categories);
}
