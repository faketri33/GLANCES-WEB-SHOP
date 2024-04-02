package com.faketri.market;

import com.faketri.market.infastructure.product.payload.product.gateway.filter.ProductSpecification;
import com.faketri.market.usecase.product.payload.product.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
class MarketApplicationTests {

    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private ProductSpecification productSpecification;

    @Test
    void contextLoads() {
    }

    @Test
    void specificationResult() {
    }

}
