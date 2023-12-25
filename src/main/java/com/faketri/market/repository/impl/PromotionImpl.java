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

    private final String basicSQL =
        "select p.id as promotion_id, p.title as promotion_title, p.banner as promotion_banner, p.discription as promotion_discription, p.date_of_start as promotion_start, p.date_of_end as promotion_end, " +
        "pr.id as product_id, pr.brand_id as brand_id, br.name as brand_name, pr.name_model as product_name_model, pr.price as product_price, pr.quantity as product_quantity, " +
        "pr.quntitysold as product_quantitysold, pr.categories_id as categories_id, cat.name as categories_name, pr.is_promotion as product_is_promotion, " +
        "pr.promotion_price as product_promotion_price, ch.id as characterostics_id, ch.name as characterostics_name, ch.value characterostics_value, " +
        "ppi.discount as promotion_item_discount,  r.id as rating_id, r.product_id as rating_product_id, r.user_id as user_id, " +
        "r.description as rating_description, r.grate as rating_grate, u.login as user_login, i.id as image_id, i.image " +
        "from promotion p " +
        "left join promotion_product_item ppi on ppi.promotion_id = p.id " +
        "left join product pr on pr.id = ppi.product_id " +
        "left join rating r on r.product_id = pr.id " +
        "left join \"user\" u on u.id = r.user_id " +
        "left join brand br on br.id = pr.brand_id " +
        "left join categories cat on pr.categories_id = cat.id " +
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

        entity.getProducts().forEach((promotionItem) ->
                    template.update("insert into promotion_product_item(promotion_id, product_id, discount) " +
                            "values(:promotion_id, :product_id, :discount)",
                            Map.of("promotion_id", promotionId,
                                    "product_id", promotionItem.getProduct().getId(),
                                    "discount", promotionItem.getDiscount())
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
