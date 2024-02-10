package com.faketri.market.repository;

import com.faketri.market.domain.order.EStatusOrder;
import com.faketri.market.domain.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Override
    Optional<Orders> findById(Long aLong);

    @Transactional
    @Modifying
    @Query("update Orders o set o.statusOrder = ?1 where o.id = ?2")
    int updateStatusOrderById(EStatusOrder statusOrder, Long id);

    @Query("select o from Orders o where o.users.id = :id")
    List<Orders> findByUsers_Id(@Param("id") Long id);

}