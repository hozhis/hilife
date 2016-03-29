package cn.dolphinsoft.hilife.product.dto;

import java.io.Serializable;
import java.util.Date;

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

    private String image;

    private Integer typeId;

    private String introduction;

    private Integer price;

    private Integer mount;

    private Date createDate;

    private Date modifyDate;

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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getMount() {
        return mount;
    }

    public void setMount(Integer mount) {
        this.mount = mount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

}
