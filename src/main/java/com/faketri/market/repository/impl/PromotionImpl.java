package com.faketri.market.repository.impl;

import com.faketri.market.entity.Promotion;
import com.faketri.market.repository.impl.mapper.PromotionExtractor;
import com.faketri.market.repository.impl.mapper.PromotionRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
public class PromotionImpl implements com.faketri.market.repository.Repository<Long, Promotion> {

    private final String baseSql = "select p.id as promotion_id, p.banner, p.title, p.discription, p.date_of_start, " +
            "p.date_if_end, product.id, product.name_model, product.categories_id, product.price, product.quantity, " +
            "product.quntitysold, product.is_promotion, product.promotion_price, brand.id as brand_id, " +
            "brand.name as brand_name, i.id AS image_id, i.image, c.name as categories_name " +
            "from promotion p " +
            "left join promotion_product_item ppi on ppi.promotion_id = p.id " +
            "left join product on product.id = ppi.product_id " +
            "left join brand on brand.id = product.brand_id " +
            "left join product_image on product_image.product_id = product.id " +
            "left join image i on i.id = product_image.image_id " +
            "left join categories c on c.id = product.id ";

    @Autowired
    private NamedParameterJdbcTemplate template;
    @Override
    public Optional<Promotion> findById(Long id) {
        return Optional.ofNullable(
                template.queryForObject(
                        baseSql + "where p.id = :id",
                        Map.of("id", id), new PromotionRowMapper()));
    }

    @Override
    public List<Promotion> findAll() {
        return template.query(baseSql, new PromotionExtractor());
    }

    @Override
    public Page<Promotion> findAll(Pageable pageable) {
        return new PageImpl<>(
                Objects.requireNonNull(template.query(baseSql, new PromotionExtractor())), pageable, countAll()
        );
    }

    @Override
    public Long save(Promotion entity) {
        return null;
    }

    @Override
    public Boolean update(Promotion entity) {
        return null;
    }

    @Override
    public Boolean delete(Promotion entity) {
        return null;
    }

    @Override
    public int countAll() {
        return template.query("select count(*) from promotion", (rs, numRow) ->
             rs.getInt(1)
        ).get(0);
    }
}
