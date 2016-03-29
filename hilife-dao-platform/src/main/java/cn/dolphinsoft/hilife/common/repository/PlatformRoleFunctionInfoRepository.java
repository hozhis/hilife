package cn.dolphinsoft.hilife.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.dolphinsoft.hilife.common.domain.PlatformRoleFunction;

/**
 * Class Name: PlatformRoleFunctionInfoRepository
 * 
 * Description: 平台角色权限关联表DAO层
 * 
 * @author hozhis
 *
 */
public interface PlatformRoleFunctionInfoRepository
        extends JpaRepository<PlatformRoleFunction, Integer>, JpaSpecificationExecutor<PlatformRoleFunction> {

    @Query(value = "SELECT * FROM PLATFORM_ROLE_FUNCTION T WHERE T.ROLE_ID=?1", nativeQuery = true)
    List<PlatformRoleFunction> findAll(Integer roleId);

    @Modifying
    @Query(value = "delete from PlatformRoleFunction t where t.roleId=?1")
    void deleteByRoleId(Integer roleId);

}
