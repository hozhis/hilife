package cn.dolphinsoft.hilife.common.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.dolphinsoft.hilife.common.domain.Product;

public interface IProductRepository extends JpaRepository<Product, Serializable>, JpaSpecificationExecutor<Product> {

    Product findByImage(String image);

    Product findByProductIdAndStatus(Integer productId, String status);

    Product findByProductId(Integer productId);
}
