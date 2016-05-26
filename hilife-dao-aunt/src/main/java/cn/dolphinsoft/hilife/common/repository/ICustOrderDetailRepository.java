package cn.dolphinsoft.hilife.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.dolphinsoft.hilife.common.domain.CustOrderDetail;


public interface ICustOrderDetailRepository extends JpaRepository<CustOrderDetail, Integer> {

    List<CustOrderDetail> findByOrderId(Integer orderId);

}
