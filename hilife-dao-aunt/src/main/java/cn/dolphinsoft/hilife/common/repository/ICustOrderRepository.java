package cn.dolphinsoft.hilife.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.dolphinsoft.hilife.common.domain.CustOrder;

public interface ICustOrderRepository extends JpaRepository<CustOrder, Integer>, JpaSpecificationExecutor<CustOrder> {

    @Query(value = "update CustOrder c set c.dispatchTime = now(), c.orderStatus = 301 where c.orderId = ?1 and c.orderStatus = 201 and orderType = 1")
    @Modifying
    void startOrder(Integer orderId);

    CustOrder findByOrderId(Integer orderId);

    @Query(value = "update CustOrder c set c.finishTime = now(), c.orderStatus = 501 where c.orderId = ?1 and c.orderStatus = 301 and orderType = 1")
    @Modifying
    void finishOrder(Integer orderId);

    @Query("from CustOrder c where c.auntId = ?1 and c.orderStatus in(201,301) and orderType = 1")
    List<CustOrder> findAllOrder(Integer auntId);
}
