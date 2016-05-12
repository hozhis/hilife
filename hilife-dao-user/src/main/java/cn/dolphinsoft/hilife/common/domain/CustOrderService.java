package cn.dolphinsoft.hilife.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUST_ORDER_SERVICE")
public class CustOrderService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ORDER_ID")
    private Integer orderId;

    @Column(name = "ORDER_PARA_VALUE1")
    private String paraValue1;

    @Column(name = "ORDER_PARA_VALUE2")
    private String paraValue2;

    @Column(name = "ORDER_PARA_VALUE3")
    private String paraValue3;

    @Column(name = "ORDER_PARA_VALUE4")
    private String paraValue4;

    @Column(name = "ORDER_PARA_VALUE5")
    private String paraValue5;

    @Column(name = "ORDER_PARA_VALUE6")
    private String paraValue6;

    @Column(name = "REMARK")
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getParaValue1() {
        return paraValue1;
    }

    public void setParaValue1(String paraValue1) {
        this.paraValue1 = paraValue1;
    }

    public String getParaValue2() {
        return paraValue2;
    }

    public void setParaValue2(String paraValue2) {
        this.paraValue2 = paraValue2;
    }

    public String getParaValue3() {
        return paraValue3;
    }

    public void setParaValue3(String paraValue3) {
        this.paraValue3 = paraValue3;
    }

    public String getParaValue4() {
        return paraValue4;
    }

    public void setParaValue4(String paraValue4) {
        this.paraValue4 = paraValue4;
    }

    public String getParaValue5() {
        return paraValue5;
    }

    public void setParaValue5(String paraValue5) {
        this.paraValue5 = paraValue5;
    }

    public String getParaValue6() {
        return paraValue6;
    }

    public void setParaValue6(String paraValue6) {
        this.paraValue6 = paraValue6;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
