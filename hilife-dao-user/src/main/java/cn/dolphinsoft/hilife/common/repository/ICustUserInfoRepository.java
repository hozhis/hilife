package cn.dolphinsoft.hilife.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.dolphinsoft.hilife.common.domain.CustUserInfo;

public interface ICustUserInfoRepository
        extends JpaRepository<CustUserInfo, Integer>, JpaSpecificationExecutor<CustUserInfo> {

    CustUserInfo findByToken(String token);

    CustUserInfo findByLoginId(String loginId);

    CustUserInfo findOne(Integer custId);

    @Query("update CustUserInfo c set c.token = null where c.token = ?1")
    @Modifying
    void clearToken(String token);

    @Query("update CustUserInfo c set c.addressId = ?2 where c.token = ?1")
    @Modifying
    void saveServiceAddress(String token, Integer addressId);

    CustUserInfo findByInviteCode(String inviteCode);
}
