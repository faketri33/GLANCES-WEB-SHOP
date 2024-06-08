package com.faketri.market.entity.user.payload.order.gateway;

import com.faketri.market.entity.user.payload.order.model.EStatusOrder;
import com.faketri.market.entity.user.payload.order.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * The interface Order repository.
 *
 * @author Dmitriy Faketri
 */
@Repository
public interface OrderRepository extends JpaRepository<Orders, UUID> {

    @Override
    Optional<Orders> findById(UUID id);

    @Query("select o from Orders o where substring(CAST(o.id AS text), length(CAST(o.id AS text)) - :suffixLength + 1) = :suffix")
    Page<Orders> findByUuidSuffix(@Param("suffix") String suffix, @Param("suffixLength") int suffixLength, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Orders o set o.statusOrder = ?1 where o.id = ?2")
    int updateStatusOrderById(EStatusOrder statusOrder, UUID id);

    @Query("select o from Orders o where o.users.id = :id")
    Page<Orders> findByUsers_Id(@Param("id") UUID id, Pageable pageable);

}