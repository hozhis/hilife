package cn.dolphinsoft.hilife.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.dolphinsoft.hilife.common.domain.CustBalance;

public interface ICustBalanceRepository extends JpaRepository<CustBalance, Integer> {

    CustBalance findByUserId(Integer userId);
}
