package cn.dolphinsoft.hilife.city.service;

import java.util.List;

import cn.dolphinsoft.hilife.city.dto.CityDto;
import cn.dolphinsoft.hilife.common.dto.ResultDto;

public interface RegionService {

    public ResultDto<List<CityDto>> getCity();
    
}
