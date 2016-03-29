package cn.dolphinsoft.hilife.common.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * Class Name: Product
 * 
 * Description: 商品实体表
 * 
 * @author hozhis
 *
 */
@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

    private static final long serialVersionUID = -2575833586449739397L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Integer productId;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "image")
    private String image;

    @Column(name = "TYPE_ID")
    private Integer typeId;

    @Column(name = "INTRODUCTION")
    private String introduction;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "MOUNT")
    private Integer mount;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "MODIFY_DATE")
    private Date modifyDate;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "STATUS")
    private String status;

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
}
