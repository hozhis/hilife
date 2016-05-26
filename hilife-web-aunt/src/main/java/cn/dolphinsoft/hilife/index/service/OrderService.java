package cn.dolphinsoft.hilife.index.service;

import java.util.List;

import javax.transaction.Transactional;

import cn.dolphinsoft.hilife.index.dto.CustOrderDto;

public interface OrderService {

    List<CustOrderDto> findAllOrder(String token);

    @Transactional
    void startService(CustOrderDto dto);

    @Transactional
    void finishService(CustOrderDto dto);
}
