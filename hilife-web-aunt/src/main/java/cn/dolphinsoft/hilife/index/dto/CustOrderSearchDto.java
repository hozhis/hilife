package cn.dolphinsoft.hilife.index.dto;

import cn.dolphinsoft.hilife.common.dto.PagingDto;

public class CustOrderSearchDto extends PagingDto<CustOrderDto> {

    private static final long serialVersionUID = -1414035255688344457L;

    private Integer orderStatus;

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

}
