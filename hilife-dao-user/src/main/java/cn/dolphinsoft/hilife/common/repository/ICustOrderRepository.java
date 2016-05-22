package cn.dolphinsoft.hilife.common.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.dolphinsoft.hilife.common.domain.CustOrder;

public interface ICustOrderRepository extends JpaRepository<CustOrder, Integer>, JpaSpecificationExecutor<CustOrder> {

    @Query(value = "update CustOrder c set c.orderStatus = ?2,c.cancelTime = ?3 where c.orderId = ?1")
    @Modifying
    void cancelOrder(Integer orderId, Integer orderStatus, Date date);

    CustOrder findByOrderId(Integer orderId);

    @Query(value = "update CustOrder c set c.status = 0 where c.orderId = ?1")
    @Modifying
    void deleteByOrderId(Integer orderId);
}
