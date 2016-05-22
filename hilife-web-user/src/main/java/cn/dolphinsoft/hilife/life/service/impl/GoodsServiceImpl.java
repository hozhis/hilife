package cn.dolphinsoft.hilife.life.service.impl;

import java.util.ArrayList;
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
import org.springframework.util.StringUtils;

import cn.dolphinsoft.hilife.common.converter.ConverterService;
import cn.dolphinsoft.hilife.common.domain.Product;
import cn.dolphinsoft.hilife.common.domain.ProductPromote;
import cn.dolphinsoft.hilife.common.domain.ProductType;
import cn.dolphinsoft.hilife.common.enumeration.BaseStatus;
import cn.dolphinsoft.hilife.common.repository.IProductPromoteRepository;
import cn.dolphinsoft.hilife.common.repository.IProductRepository;
import cn.dolphinsoft.hilife.common.repository.IProductTypeRepository;
import cn.dolphinsoft.hilife.common.util.HiLifeUtil;
import cn.dolphinsoft.hilife.life.dto.ProductSearchDto;
import cn.dolphinsoft.hilife.life.service.GoodsService;
import cn.dolphinsoft.hilife.product.dto.ProductDto;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private IProductTypeRepository typeRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IProductPromoteRepository productPromoteRepository;

    @Override
    public String getTypeName(Integer typeId) {
        ProductType productType = typeRepository.findByTypeId(typeId);
        if (productType == null) {
            return "";
        }
        return productType.getTypeName();
    }

    @Override
    public void searchProduct(final ProductSearchDto dto) {
        Pageable pageable;
        final List<Integer> productIds = new ArrayList<>();
        if (dto.getOnsaleType() != null && !dto.getOnsaleType().equals(null)) {
            List<ProductPromote> promotes = productPromoteRepository.findByPromoteTypeId(dto.getOnsaleType());
            for (ProductPromote promote : promotes) {
                Integer productId = promote.getProductId();
                productIds.add(productId);
            }
        }
        if (dto.getOrder().equals("ASC")) {
            pageable = new PageRequest(dto.getCurrentPage() - 1, dto.getPageSize(),
                    new Sort(Direction.ASC, dto.getSortBy()));
        } else if (dto.getOrder().equals("DESC")) {
            pageable = new PageRequest(dto.getCurrentPage() - 1, dto.getPageSize(),
                    new Sort(Direction.DESC, dto.getSortBy()));
        } else {
            pageable = new PageRequest(dto.getCurrentPage() - 1, dto.getPageSize(),
                    new Sort(Direction.ASC, "productId"));
        }
        Page<Product> page = productRepository.findAll(new Specification<Product>() {

            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!StringUtils.isEmpty(dto.getSearchStr())) {
                    Predicate p1, p2;
                    p1 = cb.like(root.<String> get("productName"), HiLifeUtil.filterString(dto.getSearchStr()));
                    p2 = cb.notEqual(root.<Integer> get("typeId"), 1);
                    // Predicate predicate = cb.like(root.<String> get("productName"), dto.getSearchStr());
                    Predicate predicate = cb.and(p1, p2);
                    predicates.add(predicate);
                }
                if (dto.getTypeId() != null && !dto.getTypeId().equals(null)) {
                    Predicate predicate = cb.equal(root.<Integer> get("typeId"), dto.getTypeId());
                    predicates.add(predicate);
                }
                if (BaseStatus.EFFECT.getKey() != null) {
                    Predicate predicate = cb.equal(root.<String> get("status"), BaseStatus.EFFECT.getKey());
                    predicates.add(predicate);
                }
                if (dto.getOnsaleType() != null && !dto.getOnsaleType().equals(null)) {
                    List<Predicate> list = new ArrayList<>();
                    Predicate predicate;
                    for (Integer productId : productIds) {
                        predicate = cb.equal(root.<Integer> get("productId"), productId);
                        list.add(predicate);
                    }
                    predicate = cb.or(list.toArray(new Predicate[list.size()]));
                    predicates.add(predicate);
                }
                Predicate predicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
                query.where(predicate);
                return query.getRestriction();
            }
        }, pageable);
        dto.setTotalRecord(page.getTotalElements());
        dto.setTotalPages(page.getTotalPages());
        dto.setList(buildProductDtos(page.getContent()));
    }

    private List<ProductDto> buildProductDtos(List<Product> list) {
        List<ProductDto> productDtos = new ArrayList<>();
        ProductDto dto;
        for (Product product : list) {
            dto = ConverterService.convert(product, ProductDto.class);
            productDtos.add(dto);
        }
        return productDtos;
    }

    /*
     * @Override public void searchProductPromote(final ProductPromoteSearchDto dto) { Pageable pageable = new
     * PageRequest(dto.getCurrentPage() - 1, dto.getPageSize(), new Sort(Direction.ASC, "productId"));
     * Page<ProductPromote> page = productPromoteRepository.findAll(new Specification<ProductPromote>() {
     * 
     * @Override public Predicate toPredicate(Root<ProductPromote> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
     * List<Predicate> predicates = new ArrayList<>(); if (dto.getOnsaleType() != null &&
     * !dto.getOnsaleType().equals(null)) { Predicate predicate = cb.equal(root.<Integer> get("promoteTypeId"),
     * dto.getOnsaleType()); predicates.add(predicate); } Predicate predicate = cb.and(predicates.toArray(new
     * Predicate[predicates.size()])); query.where(predicate); return query.getRestriction(); } }, pageable);
     * dto.setTotalRecord(page.getTotalElements()); dto.setTotalPages(page.getTotalPages());
     * dto.setList(buildProductPromoteDtos(page.getContent())); }
     * 
     * // private List<ProductPromoteDto> buildProductPromoteDtos(List<ProductPromote> content) {
     * List<ProductPromoteDto> list = new ArrayList<>(); ProductPromoteDto dto; for (ProductPromote promote : content) {
     * Product product = productRepository.findByProductIdAndStatus(promote.getProductId(), BaseStatus.EFFECT.getKey());
     * if (product != null) { dto = ConverterService.convert(promote, ProductPromoteDto.class);
     * dto.setProductDto(ConverterService.convert(product, ProductDto.class)); list.add(dto); } } return list; }
     */

    @Override
    public ProductDto findByProductId(Integer productId) {
        Product product = productRepository.findByProductId(productId);
        return ConverterService.convert(product, ProductDto.class);
    }

    @Transactional
    @Override
    public void productHasReviewed(Integer productId) {
        productRepository.hasReviewed(productId);
    }
}
