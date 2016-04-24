package cn.dolphinsoft.hilife.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.dolphinsoft.hilife.common.domain.Region;

public interface IRegionRepository extends JpaRepository<Region, Integer> {

    List<Region> findByCityPy(String cityPy);

}
