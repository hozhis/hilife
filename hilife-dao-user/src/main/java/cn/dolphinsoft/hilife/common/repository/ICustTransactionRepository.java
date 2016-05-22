package cn.dolphinsoft.hilife.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.dolphinsoft.hilife.common.domain.CustTransaction;

public interface ICustTransactionRepository
        extends JpaRepository<CustTransaction, Integer>, JpaSpecificationExecutor<CustTransaction> {

}
