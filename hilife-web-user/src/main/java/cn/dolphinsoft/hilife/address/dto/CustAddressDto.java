package cn.dolphinsoft.hilife.address.dto;

import cn.dolphinsoft.hilife.common.dto.RequestDto;

public class CustAddressDto extends RequestDto {

    private static final long serialVersionUID = -7148191616715735562L;

    private Integer addressId;

    private Integer userId;

    private String addressName;

    private String status = "1";

    private Integer def = 0;// 默认不选中

    private String consignee;

    private String phone;

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDef() {
        return def;
    }

    public void setDef(Integer def) {
        this.def = def;
    }
}
