package cn.dolphinsoft.hilife.life.dto;

import cn.dolphinsoft.hilife.common.dto.PagingDto;

public class ProductPromoteSearchDto extends PagingDto<ProductPromoteDto> {

    private static final long serialVersionUID = -2427295377802577768L;

    private Integer onsaleType;

    private String sortBy = "productId";

    private String order = "ASC";

    public Integer getOnsaleType() {
        return onsaleType;
    }

    public void setOnsaleType(Integer onsaleType) {
        this.onsaleType = onsaleType;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

}
