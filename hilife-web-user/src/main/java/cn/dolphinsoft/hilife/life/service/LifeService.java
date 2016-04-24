package cn.dolphinsoft.hilife.life.service;

import java.util.List;

import cn.dolphinsoft.hilife.common.dto.BasicParaDto;
import cn.dolphinsoft.hilife.life.dto.ProductPromoteDto;

public interface LifeService {

    List<BasicParaDto> getCarouselPictures();

    List<ProductPromoteDto> getPromoteProduct();
    
}
