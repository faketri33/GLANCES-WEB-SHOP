package com.faketri.market.service;

import com.faketri.market.entity.Brand;
import com.faketri.market.repository.BrandDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService {

    @Autowired
    private BrandDao brandDao;

    public void save(Brand brand){
        brandDao.save(brand);
    }
}
