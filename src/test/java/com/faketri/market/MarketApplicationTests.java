package com.faketri.market;

import com.faketri.market.entity.product.model.child.Characteristics;
import com.faketri.market.infastructure.product.gateway.ProductService;
import com.faketri.market.infastructure.product.gateway.filter.ProductSpecification;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class MarketApplicationTests {

    @Autowired
    private ProductService       productService;
    @Autowired
    private ProductSpecification productSpecification;

    @Test
    void contextLoads() {
    }

    @Test
    void specificationResult() {

        var product = productService.findByCategoriesFilteredCharacteristics(
                PageRequest.of(1, 20),
                1L,
                List.of(new Characteristics(null, "Диагональ экрана\"", "6.7'"))
        );
        System.out.println(product);
    }

}
