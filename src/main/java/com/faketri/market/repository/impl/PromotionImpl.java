package com.faketri.market.repository.impl;

import com.faketri.market.entity.Promotion;
import com.faketri.market.repository.impl.mapper.PromotionExtractor;
import com.faketri.market.repository.impl.mapper.PromotionRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
public class PromotionImpl implements com.faketri.market.repository.Repository<Long, Promotion> {

    @Autowired
    private NamedParameterJdbcTemplate template;
    @Override
    public Optional<Promotion> findById(Long id) {
        return Optional.ofNullable(
                template.queryForObject(
                        "select p.id, p.banner, p.title, p.discription, p.date_of_start, p.date_of_end from promotion p where p.id = :id",
                        Map.of("id", id), new PromotionRowMapper()));
    }

    @Override
    public List<Promotion> findAll() {
        return template.query(
                "select * from promotion",
                Map.of(), new PromotionRowMapper()
        );
    }

    @Override
    public Page<Promotion> findAll(Pageable pageable) {
        return new PageImpl<>(
                Objects.requireNonNull(
                        template.query(
                        "select * from promotion",
                        Map.of(), new PromotionRowMapper())),
                pageable, countAll()
        );
    }

    @Override
    public Long save(Promotion entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update("insert into promotion(banner, title, discription, date_of_start, date_of_end) " +
                "values(:banner, :title, :discription, :date_of_start, :date_of_end)",
                new MapSqlParameterSource(Map.of("banner", entity.getBanner(),
                        "title", entity.getTitle(),
                        "discription", entity.getDiscription(),
                        "date_of_start", entity.getDateOfStart(),
                        "date_of_end", entity.getDateOfEnd())), keyHolder, new String[] {"id"}
        );
        Long promotionId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        entity.getProducts().forEach((key, productList) ->
                productList.forEach(product ->
                    template.update("insert into promotion_product_item(promotion_id, product_id, discount) " +
                            "values(:promotion_id, :product_id, :discount)",
                            Map.of("promotion_id", promotionId,
                                    "product_id", product.getId(),
                                    "discount", key)
                    )
                )
        );

        return promotionId;
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
