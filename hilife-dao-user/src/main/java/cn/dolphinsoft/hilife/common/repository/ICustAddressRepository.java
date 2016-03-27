package cn.dolphinsoft.hilife.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.dolphinsoft.hilife.common.domain.CustAddress;

public interface ICustAddressRepository extends JpaRepository<CustAddress, Integer> {

    CustAddress findByAddressId(Integer addressId);

    @Query("from CustAddress c where c.userId = ?1 and c.status = 1")
    List<CustAddress> findByUserId(Integer userId);

    @Query("update CustAddress c set c.def = 0 where c.status = 1 and c.userId = ?1")
    @Modifying
    void clearDefault(Integer userId);

    @Query("update CustAddress c set c.def = 1 where c.status = 1 and c.addressId = ?1")
    @Modifying
    void setDefault(Integer addressId);

    CustAddress findByAddressIdAndDef(Integer addressId, Integer def);

    @Query("update CustAddress c set c.status = 0 where c.addressId = ?1")
    @Modifying
    void delete(Integer addressId);
}
