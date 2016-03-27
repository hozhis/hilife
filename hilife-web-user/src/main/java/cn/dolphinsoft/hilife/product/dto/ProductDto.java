package cn.dolphinsoft.hilife.product.dto;

import java.io.Serializable;

/**
 * 
 * Class Name: ProductDto
 * 
 * Description: 商品表dto
 * 
 * @author hozhis
 *
 */
public class ProductDto implements Serializable {

    private static final long serialVersionUID = -8849883342830250765L;

    private Integer productId;

    private String productName;

    private String logo;

    private Integer typeId;

    private String detail;

    private Integer price;

    private Integer minHours;

    private String remark;

    private String status;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getMinHours() {
        return minHours;
    }

    public void setMinHours(Integer minHours) {
        this.minHours = minHours;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
