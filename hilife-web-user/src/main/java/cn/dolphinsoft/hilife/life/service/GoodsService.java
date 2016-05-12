package cn.dolphinsoft.hilife.life.service;

import cn.dolphinsoft.hilife.life.dto.ProductPromoteSearchDto;
import cn.dolphinsoft.hilife.life.dto.ProductSearchDto;
import cn.dolphinsoft.hilife.product.dto.ProductDto;

public interface GoodsService {

    String getTypeName(Integer typeId);

    void searchProduct(ProductSearchDto dto);

    void searchProductPromote(ProductPromoteSearchDto dto);
    
    ProductDto findByProductId(Integer productId);
}
