package cn.dolphinsoft.hilife.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.dolphinsoft.hilife.common.domain.PlatformUserInfo;

public interface PlatformUserInfoRepository
        extends JpaRepository<PlatformUserInfo, Integer>, JpaSpecificationExecutor<PlatformUserInfo> {

    List<PlatformUserInfo> findByToken(String token);

    @Query("select s from PlatformUserInfo s where s.status=?3 and s.loginId=?1 and s.password=?2")
    List<PlatformUserInfo> findByLoginIdAndPassword(String loginId, String password, String status);

    @Modifying
    @Query(value = "update PLATFORM_USER_INFO t set t.STATUS = ?2 where t.USER_ID = ?1", nativeQuery = true)
    void logout(Integer userId, String status);

    @Query(value = "select * from PLATFORM_USER_INFO t where t.STATUS=?1", nativeQuery = true)
    List<PlatformUserInfo> findUserInfos(String status);

    @Query("select r from PlatformUserInfo r where r.loginId =?1 and r.status =?2")
    PlatformUserInfo findByLoginId(String loginId, String status);

    @Modifying
    @Query(value = "update PLATFORM_USER_INFO s set s.EMAIL=:email ,  s.REMARK=:remark,s.PASSWORD=:password where s.USER_ID=:userId", nativeQuery = true)
    int update(@Param("email") String email, @Param("remark") String remark, @Param("userId") int userId,
            @Param("password") String password);

    @Query(value = "update PLATFORM_USER_INFO t set t.TOKEN = null where t.USER_ID = ?1", nativeQuery = true)
    @Modifying
    void clearToken(Integer id);

    @Query("select t from PlatformUserInfo t where t.phone=?1 and t.status =?2")
    PlatformUserInfo findByPhone(Long phone, String status);

    PlatformUserInfo findByUserId(Integer createId);

}
