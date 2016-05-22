package cn.dolphinsoft.hilife.balance.dto;

import cn.dolphinsoft.hilife.common.dto.PagingDto;

public class CustTransactionSearchDto extends PagingDto<CustTransactionDto> {

    private static final long serialVersionUID = -1220765540361944629L;

    // value 1, 最近一周. value 2, 最近一月. value 3, 最近半年
    private Integer interval;

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

}
