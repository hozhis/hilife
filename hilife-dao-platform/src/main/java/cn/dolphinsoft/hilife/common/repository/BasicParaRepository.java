package cn.dolphinsoft.hilife.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.dolphinsoft.hilife.common.domain.BasicPara;

/**
 * Class Name: BasicParaRepository Description: BasicParaRepository
 * 
 * @author hozhis
 *
 */
public interface BasicParaRepository extends JpaRepository<BasicPara, Integer>, JpaSpecificationExecutor<BasicPara> {

    @Query(value = "select a.PARA_VALUE1 from BASIC_PARA a where a.TYPE_ID=?1", nativeQuery = true)
    String findParaValue1ByTypeId(Integer id);

    List<BasicPara> findByTypeId(Integer id);

    List<BasicPara> findByTypeIdIn(Iterable<Integer> listUser);

    @Modifying
    @Query(value = "delete from BasicPara t where t.typeId=?1")
    void delete(Integer typeId);

    @Query("select a from BasicPara a where a.typeId in ?1 order by paraValue1")
    List<BasicPara> findByTypeIdInOrderByParaValue1(List<Integer> listStore);
}
