package cn.dolphinsoft.hilife.common.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class Name: BasicPara
 * 
 * Description: 基本参数表关系映射对象
 * 
 * @author hozhis
 */

@Entity
@Table(name = "BASIC_PARA")
public class BasicPara implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PARA_ID")
    private Integer paraId;

    @Column(name = "TYPE_ID")
    private Integer typeId;

    @Column(name = "PARA_NAME")
    private String paraName;

    @Column(name = "PARA_VALUE1")
    private String paraValue1;

    @Column(name = "PARA_VALUE2")
    private String paraValue2;

    @Column(name = "PARA_VALUE3")
    private String paraValue3;

    @Column(name = "PARA_VALUE4")
    private String paraValue4;

    @Column(name = "REMARK")
    private String remark;

    public Integer getParaId() {
        return paraId;
    }

    public void setParaId(Integer paraId) {
        this.paraId = paraId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getParaName() {
        return paraName;
    }

    public void setParaName(String paraName) {
        this.paraName = paraName;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
