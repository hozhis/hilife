package cn.dolphinsoft.hilife.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.dolphinsoft.hilife.common.domain.CustOrder;

public interface ICustOrderRepository extends JpaRepository<CustOrder, Integer>, JpaSpecificationExecutor<CustOrder> {

}
