package com.faketri.market.repository.impl;

import com.faketri.market.domain.Promo.Promotion;
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

    private final String basicSQL =
        "select p.id as promotion_id, p.banner, p.title, p.description, p.date_of_start, p.date_of_end, " +
            "pr.id as product_id, pr.brand_id, pr.name_model, pr.price, pr.quantity, pr.quantity_sold, pr.is_promo_active," +
            "pr.promotion_price, pr.discount, b.name as brand_name, c.name as categories_name,  i.id as image_id, i.image, " +
            "pr.categories_id, ch.id as characteristics_id, ch.name as characteristics_name, ch.value as characteristics_value " +
        "from promotion p " +
            "left join promotion_product_item ppi on ppi.promotion_id = p.id " +
            "left join product pr on pr.id = ppi.product_id " +
            "left join brand b on b.id = pr.brand_id " +
            "left join categories c on c.id = pr.categories_id " +
            "left join product_image pi on pi.product_id = pr.id " +
            "left join image i on i.id = pi.image_id " +
            "left join product_characteristics pc on pc.product_id = pr.id " +
            "left join characteristics ch on ch.id = pc.characteristics_id ";

    @Autowired
    private NamedParameterJdbcTemplate template;
    @Override
    public Optional<Promotion> findById(Long id) {
        return Optional.ofNullable(
                template.queryForObject(
                        basicSQL + " where p.id = :id",
                        Map.of("id", id), new PromotionRowMapper()));
    }

    @Override
    public Promotion findByFields(Promotion entity) {
        return null;
    }

    @Override
    public List<Promotion> findAll() {
        return template.query(
                basicSQL,
                Map.of(), new PromotionExtractor()
        ).stream().toList();
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
    public Promotion save(Promotion entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update("insert into promotion(banner, title, description, date_of_start, date_of_end) " +
                "values(:banner, :title, :description, :date_of_start, :date_of_end)",
                new MapSqlParameterSource(Map.of("banner", entity.getBanner(),
                        "title", entity.getTitle(),
                        "description", entity.getDescription(),
                        "date_of_start", entity.getDateOfStart(),
                        "date_of_end", entity.getDateOfEnd())), keyHolder, new String[] {"id"}
        );
        entity.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        if(entity.getId() != null)
            entity.getPromotionItems().forEach((promotionItem) ->
                        template.update("insert into promotion_product_item(promotion_id, product_id) " +
                                "values(:promotion_id, :product_id)",
                                Map.of("promotion_id", entity.getId(),
                                        "product_id", promotionItem.getId())
                        )
            );

        return entity;
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
