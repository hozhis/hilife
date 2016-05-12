package cn.dolphinsoft.hilife.order.service;

import cn.dolphinsoft.hilife.common.dto.ResultDto;
import cn.dolphinsoft.hilife.order.dto.CustOrderDto;
import cn.dolphinsoft.hilife.order.dto.CustOrderSearchDto;

public interface CustOrderService {

    void searchCustOrder(CustOrderSearchDto dto);

    ResultDto<String> submitOrder(CustOrderDto dto);

    void cancelOrder(Integer orderId);
}
