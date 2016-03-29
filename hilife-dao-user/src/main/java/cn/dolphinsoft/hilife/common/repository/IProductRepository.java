package cn.dolphinsoft.hilife.common.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.dolphinsoft.hilife.common.domain.Product;

public interface IProductRepository extends JpaRepository<Product, Serializable> {

    Product findByImage(String image);
}
