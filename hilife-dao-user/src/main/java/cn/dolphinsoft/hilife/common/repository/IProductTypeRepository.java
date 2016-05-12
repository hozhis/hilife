package cn.dolphinsoft.hilife.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.dolphinsoft.hilife.common.domain.ProductType;

public interface IProductTypeRepository extends JpaRepository<ProductType, Integer> {

    ProductType findByTypeId(Integer typeId);
}
