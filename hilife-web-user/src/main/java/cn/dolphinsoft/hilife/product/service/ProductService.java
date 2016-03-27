package cn.dolphinsoft.hilife.product.service;

import cn.dolphinsoft.hilife.product.dto.ProductDto;

public interface ProductService {

    /**
     * 
     * Description: 根据logo获取家政服务商品详情
     *
     * @return
     */
    ProductDto getProductDetail(String logo);

    /**
     * 
     * Description: 根据ID获取商品详情
     *
     * @param id
     * @return
     */
    ProductDto getProductDetail(Integer id);

}
