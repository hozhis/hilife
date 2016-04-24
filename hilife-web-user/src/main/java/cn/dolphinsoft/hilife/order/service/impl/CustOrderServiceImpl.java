package cn.dolphinsoft.hilife.order.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.dolphinsoft.hilife.common.authority.AuthorityContext;
import cn.dolphinsoft.hilife.common.converter.ConverterService;
import cn.dolphinsoft.hilife.common.domain.CustOrder;
import cn.dolphinsoft.hilife.common.domain.CustOrderDetail;
import cn.dolphinsoft.hilife.common.repository.ICustOrderDetailRepository;
import cn.dolphinsoft.hilife.common.repository.ICustOrderRepository;
import cn.dolphinsoft.hilife.common.repository.IProductRepository;
import cn.dolphinsoft.hilife.order.dto.CustOrderDetailDto;
import cn.dolphinsoft.hilife.order.dto.CustOrderDto;
import cn.dolphinsoft.hilife.order.dto.CustOrderSearchDto;
import cn.dolphinsoft.hilife.order.service.CustOrderService;
import cn.dolphinsoft.hilife.product.dto.ProductDto;

@Service
public class CustOrderServiceImpl implements CustOrderService {

    @Autowired
    private ICustOrderRepository orderRepository;

    @Autowired
    private ICustOrderDetailRepository orderDetailRepository;

    @Autowired
    private IProductRepository productRepository;

    @Override
    public void searchCustOrder(final CustOrderSearchDto dto) {
        Pageable pageable = new PageRequest(dto.getCurrentPage() - 1, dto.getPageSize(),
                new Sort(Direction.ASC, "createDate"));
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

}
