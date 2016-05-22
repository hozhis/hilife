package cn.dolphinsoft.hilife.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.dolphinsoft.hilife.common.domain.ProductPromote;

public interface IProductPromoteRepository
        extends JpaRepository<ProductPromote, Integer>, JpaSpecificationExecutor<ProductPromote> {

    List<ProductPromote> findByPromoteTypeId(Integer promoteTypeId);

}
