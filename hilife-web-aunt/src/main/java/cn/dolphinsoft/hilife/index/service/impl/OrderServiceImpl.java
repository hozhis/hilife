package cn.dolphinsoft.hilife.index.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dolphinsoft.hilife.common.converter.ConverterService;
import cn.dolphinsoft.hilife.common.domain.CustOrder;
import cn.dolphinsoft.hilife.common.domain.CustOrderService;
import cn.dolphinsoft.hilife.common.repository.IAuntUserInfoRepository;
import cn.dolphinsoft.hilife.common.repository.ICustOrderRepository;
import cn.dolphinsoft.hilife.common.repository.ICustOrderServiceRepository;
import cn.dolphinsoft.hilife.index.dto.CustOrderDto;
import cn.dolphinsoft.hilife.index.dto.CustOrderServiceDto;
import cn.dolphinsoft.hilife.index.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ICustOrderRepository custOrderRepository;

    @Autowired
    private ICustOrderServiceRepository orderServiceRepository;

    @Autowired
    private IAuntUserInfoRepository auntUserRepository;

    @Override
    public List<CustOrderDto> findAllOrder(String token) {
        Integer userId = auntUserRepository.findByToken(token).getUserId();
        List<CustOrder> orders = custOrderRepository.findAllOrder(userId);
        List<CustOrderDto> dtos = new ArrayList<>();
        if (orders != null && orders.size() > 0) {
            for (CustOrder order : orders) {
                CustOrderDto dto = ConverterService.convert(order, CustOrderDto.class);
                CustOrderService service = orderServiceRepository.findByOrderId(order.getOrderId());
                CustOrderServiceDto serviceDto = ConverterService.convert(service, CustOrderServiceDto.class);
                dto.setServiceDto(serviceDto);
                dtos.add(dto);
            }
        }
        return dtos;
    }

    @Override
    public void startService(CustOrderDto dto) {
        custOrderRepository.startOrder(dto.getOrderId());
    }

    @Override
    public void finishService(CustOrderDto dto) {
        custOrderRepository.finishOrder(dto.getOrderId());
    }

}
