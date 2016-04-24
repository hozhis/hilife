package cn.dolphinsoft.hilife.life.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.dolphinsoft.hilife.common.constant.BasicTypeConstant;
import cn.dolphinsoft.hilife.common.converter.ConverterService;
import cn.dolphinsoft.hilife.common.domain.BasicPara;
import cn.dolphinsoft.hilife.common.domain.Product;
import cn.dolphinsoft.hilife.common.domain.ProductPromote;
import cn.dolphinsoft.hilife.common.dto.BasicParaDto;
import cn.dolphinsoft.hilife.common.repository.IBasicParaRepository;
import cn.dolphinsoft.hilife.common.repository.IProductPromoteRepository;
import cn.dolphinsoft.hilife.common.repository.IProductRepository;
import cn.dolphinsoft.hilife.life.dto.ProductPromoteDto;
import cn.dolphinsoft.hilife.life.service.LifeService;
import cn.dolphinsoft.hilife.product.dto.ProductDto;

@Service
public class LifeServiceImpl implements LifeService {

    @Autowired
    private IBasicParaRepository paraRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IProductPromoteRepository promoteRepository;

    @Override
    public List<BasicParaDto> getCarouselPictures() {
        List<BasicParaDto> dtos = new ArrayList<>();
        List<BasicPara> paras = paraRepository.findByTypeId(BasicTypeConstant.USER_LIFE_CAROUSEL);
        if (paras != null) {
            BasicParaDto dto;
            for (BasicPara para : paras) {
                dto = ConverterService.convert(para, BasicParaDto.class);
                dtos.add(dto);
            }
        }
        return dtos;
    }

    @Override
    public List<ProductPromoteDto> getPromoteProduct() {
        List<ProductPromoteDto> dtos = new ArrayList<>();
        List<ProductPromote> promotes = promoteRepository.findAll();
        if (promotes != null) {
            Product product;
            for (ProductPromote promote : promotes) {
                product = productRepository.findByProductId(promote.getProductId());
                Assert.notNull(product);
                ProductPromoteDto dto = ConverterService.convert(promote, ProductPromoteDto.class);
                dto.setProductDto(ConverterService.convert(product, ProductDto.class));
                dtos.add(dto);
            }
        }
        return dtos;
    }

}
