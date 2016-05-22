package cn.dolphinsoft.hilife.common.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.dolphinsoft.hilife.common.domain.Product;

public interface IProductRepository extends JpaRepository<Product, Serializable>, JpaSpecificationExecutor<Product> {

    Product findByImage(String image);

    Product findByProductIdAndStatus(Integer productId, String status);

    Product findByProductId(Integer productId);

    @Query("update Product p set p.reviews = p.reviews + 1 where p.productId = ?1")
    @Modifying
    void hasReviewed(Integer productId);
}
