package cn.dolphinsoft.hilife.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.dolphinsoft.hilife.common.converter.ConverterService;
import cn.dolphinsoft.hilife.common.domain.Product;
import cn.dolphinsoft.hilife.common.repository.IProductRepository;
import cn.dolphinsoft.hilife.product.dto.ProductDto;
import cn.dolphinsoft.hilife.product.service.ProductService;

/**
 * 
 * Class Name: ProductServiceImpl
 * 
 * Description: 商品服务项业务实现类
 * 
 * @author hozhis
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public ProductDto getProductDetail(String logo) {
        Product product = productRepository.findByLogo(logo);
        // 如果product查不到，则业务上有问题
        Assert.notNull(product);
        return ConverterService.convert(product, ProductDto.class);
    }

    @Override
    public ProductDto getProductDetail(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

}
