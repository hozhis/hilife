package cn.dolphinsoft.hilife.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.dolphinsoft.hilife.common.domain.CustCoupon;

public interface ICustCouponRepository extends JpaRepository<CustCoupon, Integer> {

    @Query("from CustCoupon where userId = ?1 and couponId = ?2 and expireDate >= curdate()")
    List<CustCoupon> findByUserIdAndCouponId(Integer userId, Integer couponId);
    
    @Query("select count(*) from CustCoupon where userId = ?1 and datediff(expireDate,curdate()) <= 7 and expireDate >= curdate()")
    Integer getExpCount(Integer userId);

}
