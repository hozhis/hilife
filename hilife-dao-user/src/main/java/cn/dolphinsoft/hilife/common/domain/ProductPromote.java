package cn.dolphinsoft.hilife.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_PROMOTE")
public class ProductPromote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROMOTE_ID")
    private Integer promoteId;

    @Column(name = "PRODUCT_ID")
    private Integer productId;

    @Column(name = "PROMOTE_TYPE_ID")
    private Integer promoteTypeId;

    @Column(name = "PROMOTE_TYPE_NAME")
    private String promoteTypeName;

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
