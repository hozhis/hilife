package cn.dolphinsoft.hilife.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.dolphinsoft.hilife.common.domain.PlatformFunctionInfo;

/**
 * Class Name: PlatformFunctionInfoRepository
 * 
 * Description: 平台权限DAO层
 * 
 * @author hozhis
 *
 */
public interface PlatformFunctionInfoRepository
        extends JpaRepository<PlatformFunctionInfo, Integer>, JpaSpecificationExecutor<PlatformFunctionInfo> {

    @Query(value = "SELECT * FROM PLATFORM_FUNCTION_INFO WHERE LOCATE(?1, PARENT_IDS) > 0 OR FUNCTION_ID = 0 ORDER BY LEVEL DESC, PRIORITY DESC, FUNCTION_ID DESC", nativeQuery = true)
    List<PlatformFunctionInfo> findFunctionTreeList(String string);

    @Query("select f from PlatformFunctionInfo f where f.functionId in (select rf.functionId from PlatformRoleFunction rf where rf.roleId in (?1))")
    List<PlatformFunctionInfo> findFunctionByRoleIds(Iterable<Integer> roleIds);

    List<PlatformFunctionInfo> findByType(String type);

    @Query("select t from PlatformFunctionInfo t where t.functionId in ?1")
    List<PlatformFunctionInfo> findByFunctionIdIn(Iterable<Integer> functionIds);

    @Query(value = "SELECT function_id FROM PLATFORM_FUNCTION_INFO where LOCATE(?1, PARENT_IDS) > 0 or function_id =?2", nativeQuery = true)
    List<Integer> findByLevelId(String id, Integer functionId);

}
