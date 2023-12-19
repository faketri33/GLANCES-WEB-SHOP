package com.faketri.market.repository;

import com.faketri.market.entity.Brand;
import com.faketri.market.entity.Categories;
import com.faketri.market.entity.Characteristics;
import com.faketri.market.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface BrandRepository extends Repository<Long, Brand> {

}
