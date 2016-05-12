package cn.dolphinsoft.hilife.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.dolphinsoft.hilife.common.domain.CustOrderService;

public interface ICustOrderServiceRepository extends JpaRepository<CustOrderService, Integer> {

}
