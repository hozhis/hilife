package cn.dolphinsoft.hilife.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import cn.dolphinsoft.hilife.common.authority.AuthorityContext;
import cn.dolphinsoft.hilife.common.converter.ConverterService;
import cn.dolphinsoft.hilife.common.domain.CustOrder;
import cn.dolphinsoft.hilife.common.domain.CustOrderDetail;
import cn.dolphinsoft.hilife.common.domain.Product;
import cn.dolphinsoft.hilife.common.dto.ResultDto;
import cn.dolphinsoft.hilife.common.dto.ResultDtoFactory;
import cn.dolphinsoft.hilife.common.enumeration.BaseStatus;
import cn.dolphinsoft.hilife.common.enumeration.OrderStatus;
import cn.dolphinsoft.hilife.common.repository.ICustOrderDetailRepository;
import cn.dolphinsoft.hilife.common.repository.ICustOrderRepository;
import cn.dolphinsoft.hilife.common.repository.ICustOrderServiceRepository;
import cn.dolphinsoft.hilife.common.repository.IProductRepository;
import cn.dolphinsoft.hilife.order.dto.CustOrderDetailDto;
import cn.dolphinsoft.hilife.order.dto.CustOrderDto;
import cn.dolphinsoft.hilife.order.dto.CustOrderSearchDto;
import cn.dolphinsoft.hilife.order.service.CustOrderService;
import cn.dolphinsoft.hilife.product.dto.CustOrderServiceDto;
import cn.dolphinsoft.hilife.product.dto.ProductDto;

@Service
public class CustOrderServiceImpl implements CustOrderService {

    @Autowired
    private ICustOrderRepository orderRepository;

    @Autowired
    private ICustOrderDetailRepository orderDetailRepository;

    @Autowired
    private ICustOrderServiceRepository orderServiceRepository;

    @Autowired
    private IProductRepository productRepository;

    @Override
    public void searchCustOrder(final CustOrderSearchDto dto) {
        Pageable pageable = new PageRequest(dto.getCurrentPage() - 1, dto.getPageSize(),
                new Sort(Direction.DESC, "createDate"));
        Page<CustOrder> page = orderRepository.findAll(new Specification<CustOrder>() {

            @Override
            public Predicate toPredicate(Root<CustOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!StringUtils.isEmpty(dto.getOrderStatus())) {
                    Predicate predicate = cb.equal(root.<Integer> get("orderStatus"), dto.getOrderStatus());
                    predicates.add(predicate);
                }
                Predicate predicate = cb.equal(root.<Integer> get("userId"),
                        AuthorityContext.getCurrentUser().getUserId());
                predicates.add(predicate);
                predicate = cb.equal(root.<String> get("status"), BaseStatus.EFFECT.getKey());
                predicates.add(predicate);
                predicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
                query.where(predicate);
                return query.getRestriction();
            }
        }, pageable);
        dto.setTotalRecord(page.getTotalElements());
        dto.setTotalPages(page.getTotalPages());
        dto.setList(buildOrderDtos(page.getContent()));
    }

    private List<CustOrderDto> buildOrderDtos(List<CustOrder> list) {
        List<CustOrderDto> orderDtos = new ArrayList<>();
        for (CustOrder order : list) {
            CustOrderDto dto = ConverterService.convert(order, CustOrderDto.class);
            List<CustOrderDetail> orderDetails = orderDetailRepository.findByOrderId(order.getOrderId());
            if (orderDetails != null) {
                List<CustOrderDetailDto> dtos = new ArrayList<>();
                for (CustOrderDetail orderDetail : orderDetails) {
                    ProductDto productDto = ConverterService
                            .convert(productRepository.findByProductId(orderDetail.getProductId()), ProductDto.class);
                    CustOrderDetailDto orderDetailDto = ConverterService.convert(orderDetail, CustOrderDetailDto.class);
                    orderDetailDto.setProductDto(productDto);
                    dtos.add(orderDetailDto);
                }
                dto.setList(dtos);
            }
            orderDtos.add(dto);
        }
        return orderDtos;
    }

    @Override
    public ResultDto<String> submitOrder(CustOrderDto dto) {
        if (dto.getOrderType().equals(1)) {// 服务订单
            CustOrder order = new CustOrder();
            order.setUserId(AuthorityContext.getCurrentUser().getUserId());
            order.setCreateDate(new Date());
            order.setOrderType(dto.getOrderType());
            order.setTotalAmount(dto.getTotalAmount());
            order.setAuntId(dto.getAuntId());
            order.setOrderStatus(OrderStatus.RECEIVE.getKey());
            order.setServiceAddress(dto.getServiceAddress());
            order.setPhone(dto.getPhone());
            order.setCustName(dto.getCustName());
            order.setStatus(BaseStatus.EFFECT.getKey());
            orderRepository.save(order);
            CustOrderDetail detail = new CustOrderDetail();
            detail.setOrderId(order.getOrderId());
            detail.setProductId(dto.getList().get(0).getProductId());
            detail.setPrice(dto.getList().get(0).getPrice());
            detail.setAmount(dto.getList().get(0).getAmount());
            orderDetailRepository.save(detail);
            cn.dolphinsoft.hilife.common.domain.CustOrderService orderService = ConverterService
                    .convert(dto.getServiceDto(), cn.dolphinsoft.hilife.common.domain.CustOrderService.class);
            orderService.setOrderId(order.getOrderId());
            orderServiceRepository.save(orderService);
            return ResultDtoFactory.toAck("提交成功");
        } else if (dto.getOrderType().equals(0)) {// 商品订单

        }
        return ResultDtoFactory.toNack("提交失败");
    }

    @Transactional
    @Override
    public void cancelOrder(Integer orderId) {
        orderRepository.cancelOrder(orderId, OrderStatus.CANCEL.getKey(), new Date());
    }

    @Override
    public CustOrderDto showOrderDetail(Integer orderId) {
        CustOrder order = orderRepository.findByOrderId(orderId);
        Assert.notNull(order);
        CustOrderDto dto = ConverterService.convert(order, CustOrderDto.class);
        List<CustOrderDetail> details = orderDetailRepository.findByOrderId(orderId);
        Assert.notEmpty(details);
        List<CustOrderDetailDto> list = new ArrayList<>();
        for (CustOrderDetail detail : details) {
            CustOrderDetailDto orderDetailDto = ConverterService.convert(detail, CustOrderDetailDto.class);
            Product product = productRepository.findByProductId(detail.getProductId());
            ProductDto dto2 = ConverterService.convert(product, ProductDto.class);
            orderDetailDto.setProductDto(dto2);
            list.add(orderDetailDto);
        }
        dto.setList(list);
        cn.dolphinsoft.hilife.common.domain.CustOrderService service = orderServiceRepository.findByOrderId(orderId);
        if (service != null) {
            dto.setServiceDto(ConverterService.convert(service, CustOrderServiceDto.class));
        }
        return dto;
    }

    @Transactional
    @Override
    public void deleteOrder(Integer orderId) {
        orderRepository.deleteByOrderId(orderId);
    }

}
