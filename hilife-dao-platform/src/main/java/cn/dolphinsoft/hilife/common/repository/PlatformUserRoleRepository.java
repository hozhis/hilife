package cn.dolphinsoft.hilife.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.dolphinsoft.hilife.common.domain.PlatformUserRole;

public interface PlatformUserRoleRepository
        extends JpaRepository<PlatformUserRole, Integer>, JpaSpecificationExecutor<PlatformUserRole> {

    @Modifying
    @Query(value = "delete from PlatformUserRole t where t.userId=?1")
    void deleteByUserId(Integer userId);

}
