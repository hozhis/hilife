package cn.dolphinsoft.hilife.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.dolphinsoft.hilife.common.domain.AuntUserInfo;

public interface IAuntUserInfoRepository
        extends JpaRepository<AuntUserInfo, Integer>, JpaSpecificationExecutor<AuntUserInfo> {

    AuntUserInfo findByToken(String token);

    AuntUserInfo findByLoginId(String loginId);

    AuntUserInfo findOne(Integer custId);

    @Query("update AuntUserInfo a set a.token = null where a.token = ?1")
    @Modifying
    void clearToken(String token);

}
