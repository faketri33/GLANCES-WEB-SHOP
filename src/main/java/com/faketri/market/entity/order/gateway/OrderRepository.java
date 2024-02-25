package com.faketri.market.entity.order.gateway;

import com.faketri.market.entity.order.model.EStatusOrder;
import com.faketri.market.entity.order.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * The interface Order repository.
 *
 * @author Dmitriy Faketri
 */
@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Override
    Optional<Orders> findById(Long aLong);

    /**
     * Update status order by id int.
     *
     * @param statusOrder the status order
     * @param id          the id
     *
     * @return the int
     */
    @Transactional
    @Modifying
    @Query("update Orders o set o.statusOrder = ?1 where o.id = ?2")
    int updateStatusOrderById(EStatusOrder statusOrder, Long id);

    /**
     * Find by users id list.
     *
     * @param id the id
     *
     * @return the list
     */
    @Query("select o from Orders o where o.users.id = :id")
    List<Orders> findByUsers_Id(@Param("id") Long id);

}