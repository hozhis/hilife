package cn.dolphinsoft.hilife.city.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dolphinsoft.hilife.city.dto.CityDto;
import cn.dolphinsoft.hilife.city.service.RegionService;
import cn.dolphinsoft.hilife.common.domain.Region;
import cn.dolphinsoft.hilife.common.dto.ResultDto;
import cn.dolphinsoft.hilife.common.dto.ResultDtoFactory;
import cn.dolphinsoft.hilife.common.repository.IRegionRepository;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private IRegionRepository regionRepository;

    @Override
    public ResultDto<List<CityDto>> getCity() {
        List<CityDto> lists = new ArrayList<>();
        CityDto dto;
        String[] py = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
                "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
        for (int i = 0; i < py.length; i++) {
            List<Region> list = regionRepository.findByCityPy(py[i]);
            if (list != null && list.size() > 0) {
                List<String> citys = new ArrayList<>();
                for(Region region : list){
                    citys.add(region.getAreaname());
                }
                dto = new CityDto();
                dto.setPy(py[i]);
                dto.setCity(citys);
                lists.add(dto);
            }
        }
        return ResultDtoFactory.toAck("success", lists);
    }

}
