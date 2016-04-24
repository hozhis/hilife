package cn.dolphinsoft.hilife.life.dto;

import java.io.Serializable;

import cn.dolphinsoft.hilife.product.dto.ProductDto;

public class ProductPromoteDto implements Serializable {

    private static final long serialVersionUID = 8124247063921939164L;

    private Integer promoteId;

    private Integer productId;

    private ProductDto productDto;

    private Integer promoteTypeId;

    private String promoteTypeName;

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public Integer getPromoteId() {
        return promoteId;
    }

    public void setPromoteId(Integer promoteId) {
        this.promoteId = promoteId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPromoteTypeId() {
        return promoteTypeId;
    }

    public void setPromoteTypeId(Integer promoteTypeId) {
        this.promoteTypeId = promoteTypeId;
    }

    public String getPromoteTypeName() {
        return promoteTypeName;
    }

    public void setPromoteTypeName(String promoteTypeName) {
        this.promoteTypeName = promoteTypeName;
    }

}
