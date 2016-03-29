package cn.dolphinsoft.hilife.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.dolphinsoft.hilife.common.domain.BasicPara;

/**
 * Class Name: IBasicParaRepository Description: 基本参数repository
 * 
 * @author hozhis
 *
 */
public interface IBasicParaRepository extends JpaRepository<BasicPara, Integer>, JpaSpecificationExecutor<BasicPara> {

    List<BasicPara> findByTypeId(Integer typeId);

    /**
     * Description: 根据paraName查询数据
     * 
     * @param paraName
     * @return
     */
    List<BasicPara> findByParaName(String paraName);

    /**
     * Description: 根据paraName查询paraValue1
     * 
     * @param paraName
     * @return
     */
    @Query(value = "select para_value1 from BASIC_PARA where para_name =:paraName", nativeQuery = true)
    String findParaValue1ByParaName(@Param("paraName") String paraName);

}
