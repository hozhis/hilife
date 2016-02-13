package cn.dolphinsoft.hilife.common.dto;

import java.util.List;

/**
 * Class Name: PagingDto Description:分页Dto
 * 
 * @author hozhis
 *
 */
public class PagingDto<T> extends RequestDto {

    private static final long serialVersionUID = 1L;
    // 每页显示记录数
    private Integer pageSize = 20;
    // 当前页数
    private Integer currentPage = 1;
    // 总记录数
    private Long totalRecord;
    // 总页数
    private Integer totalPages;
    // 数据List
    private List<T> list;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Long totalRecord) {
        this.totalRecord = totalRecord;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
