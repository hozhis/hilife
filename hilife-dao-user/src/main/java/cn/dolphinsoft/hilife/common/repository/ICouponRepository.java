package cn.dolphinsoft.hilife.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.dolphinsoft.hilife.common.domain.Coupon;

public interface ICouponRepository extends JpaRepository<Coupon, Integer> {

    List<Coupon> findByStatus(String status);
    
    List<Coupon> findByTypeIdAndStatus(Integer typeId,String status);
}
