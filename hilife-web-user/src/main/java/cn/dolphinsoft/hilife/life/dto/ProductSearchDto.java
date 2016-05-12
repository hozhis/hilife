package cn.dolphinsoft.hilife.life.dto;

import cn.dolphinsoft.hilife.common.dto.PagingDto;
import cn.dolphinsoft.hilife.product.dto.ProductDto;

public class ProductSearchDto extends PagingDto<ProductDto> {

    private static final long serialVersionUID = -261846890300682358L;

    private Integer onsaleType;

    private String searchStr;

    private Integer typeId;

    private String sortBy = "productId";

    private String order = "ASC";

    public Integer getOnsaleType() {
        return onsaleType;
    }

    public void setOnsaleType(Integer onsaleType) {
        this.onsaleType = onsaleType;
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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
