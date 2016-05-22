package cn.dolphinsoft.hilife.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.dolphinsoft.hilife.common.domain.BasicType;

/**
 * Class Name: BasicTypeRepository Description: BasicTypeRepository
 * 
 * @author hozhis
 *
 */
public interface IBasicTypeRepository extends JpaRepository<BasicType, Integer>, JpaSpecificationExecutor<BasicType> {

}
