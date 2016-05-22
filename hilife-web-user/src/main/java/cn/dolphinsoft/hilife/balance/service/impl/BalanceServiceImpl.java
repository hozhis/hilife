package cn.dolphinsoft.hilife.balance.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import cn.dolphinsoft.hilife.balance.dto.CustTransactionDto;
import cn.dolphinsoft.hilife.balance.dto.CustTransactionSearchDto;
import cn.dolphinsoft.hilife.balance.service.BalanceService;
import cn.dolphinsoft.hilife.common.authority.AuthorityContext;
import cn.dolphinsoft.hilife.common.converter.ConverterService;
import cn.dolphinsoft.hilife.common.domain.CustBalance;
import cn.dolphinsoft.hilife.common.domain.CustTransaction;
import cn.dolphinsoft.hilife.common.repository.ICustBalanceRepository;
import cn.dolphinsoft.hilife.common.repository.ICustTransactionRepository;
import cn.dolphinsoft.hilife.common.util.DateTimeUtil;

@Service
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    private ICustBalanceRepository balanceRepository;

    @Autowired
    private ICustTransactionRepository transactionRepository;

    @Override
    public Integer getBalance() {
        CustBalance balance = balanceRepository.findByUserId(AuthorityContext.getCurrentUser().getUserId());
        if (balance != null) {
            return balance.getBalance();
        }
        return 0;
    }

    @Override
    public void searchTransaction(final CustTransactionSearchDto dto) {
        Pageable pageable = new PageRequest(dto.getCurrentPage() - 1, dto.getPageSize(),
                new Sort(Direction.DESC, "transTime"));
        Page<CustTransaction> page = transactionRepository.findAll(new Specification<CustTransaction>() {
            @Override
            public Predicate toPredicate(Root<CustTransaction> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat(DateTimeUtil.SIMPLE_FMT);
                sdf.format(date);
                Calendar calendar = Calendar.getInstance();
                if (dto.getInterval().equals(1)) {
                    calendar.add(Calendar.DAY_OF_YEAR, -7);
                } else if (dto.getInterval().equals(2)) {
                    calendar.add(Calendar.MONTH, -1);
                } else {
                    calendar.add(Calendar.MONTH, -6);
                }
                Date eDate = calendar.getTime();
                sdf.format(eDate);
                Predicate predicate = cb.between(root.<Date> get("transTime"), eDate, date);
                predicates.add(predicate);
                predicate = cb.equal(root.<Integer> get("userId"), AuthorityContext.getCurrentUser().getUserId());
                predicates.add(predicate);
                predicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
                query.where(predicate);
                return query.getRestriction();
            }

        }, pageable);
        dto.setTotalRecord(page.getTotalElements());
        dto.setTotalPages(page.getTotalPages());
        dto.setList(buildTransactionDtos(page.getContent()));
    }

    private List<CustTransactionDto> buildTransactionDtos(List<CustTransaction> content) {
        List<CustTransactionDto> dtos = new ArrayList<>();
        for (CustTransaction transaction : content) {
            CustTransactionDto dto = ConverterService.convert(transaction, CustTransactionDto.class);
            dto.setTransTime(DateTimeUtil.parseDateToString(transaction.getTransTime(), "MM-dd HH:mm"));
            dtos.add(dto);
        }
        return dtos;
    }

}
