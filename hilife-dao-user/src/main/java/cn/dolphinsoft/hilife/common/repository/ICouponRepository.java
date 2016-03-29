package cn.dolphinsoft.hilife.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.dolphinsoft.hilife.common.domain.Coupon;

public interface ICouponRepository extends JpaRepository<Coupon, Integer> {

}
