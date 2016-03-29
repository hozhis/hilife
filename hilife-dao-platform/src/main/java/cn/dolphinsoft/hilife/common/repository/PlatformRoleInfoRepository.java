package cn.dolphinsoft.hilife.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.dolphinsoft.hilife.common.domain.PlatformRoleInfo;

public interface PlatformRoleInfoRepository
        extends JpaRepository<PlatformRoleInfo, Integer>, JpaSpecificationExecutor<PlatformRoleInfo> {

    @Query("select R from PlatformRoleInfo R ,PlatformUserRole UR,PlatformUserInfo U WHERE UR.userId =U.userId AND R.roleId = UR.roleId AND R.status=?2 AND U.token=?1")
    List<PlatformRoleInfo> findRoleInfoByToken(String token, String status);

    @Query("select r from PlatformRoleInfo r ,PlatformUserRole t where r.roleId = t.roleId and t.userId=?1)")
    List<PlatformRoleInfo> findByUserId(Integer userId);

    @Modifying
    @Query(value = "update PLATFORM_ROLE_INFO t set t.STATUS =?2 where t.ROLE_ID=?1", nativeQuery = true)
    void logout(Integer roleId, String status);

    @Query("select r from PlatformRoleInfo r where r.status=?1")
    List<PlatformRoleInfo> findAllByStatus(String status);

    @Query("select r.status from PlatformRoleInfo r where r.role=?1 and r.status=?2")
    String findByRoleAndStatus(String role, String status);

    @Query("select a from PlatformRoleInfo a , PlatformUserRole b , PlatformUserInfo c where a.roleId=?1 and a.roleId=b.roleId and b.userId=c.userId and c.status =1 ")
    List<PlatformRoleInfo> findByRoleId(Integer roleId);
}
