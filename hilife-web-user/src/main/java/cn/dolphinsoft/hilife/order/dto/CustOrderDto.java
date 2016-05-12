package cn.dolphinsoft.hilife.order.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.dolphinsoft.hilife.common.dto.RequestDto;
import cn.dolphinsoft.hilife.product.dto.CustOrderServiceDto;

public class CustOrderDto extends RequestDto {

    private static final long serialVersionUID = 4893263013430815107L;

    private Integer orderId;

    private Integer userId;

    private List<CustOrderDetailDto> list = new ArrayList<>();

    private Date createDate;

    private Integer orderType; // 1 - 服务订单， 0 - 商品订单

    private Integer totalAmount;

    private Integer auntId;

    private Integer orderStatus;

    private String serviceAddress;

    private Integer evaluateStar;

    private String evaluateContent;

    private Date receiveTime;

    private Date dispatchTime;

    private Date payTime;

    private Date evaluateTime;

    private Date closeTime;

    private Date cancelTime;

    private Date finishTime;

    private String status;

    private CustOrderServiceDto serviceDto = new CustOrderServiceDto();

    public CustOrderServiceDto getServiceDto() {
        return serviceDto;
    }

    public void setServiceDto(CustOrderServiceDto serviceDto) {
        this.serviceDto = serviceDto;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(Date evaluateTime) {
        this.evaluateTime = evaluateTime;
    }

    public List<CustOrderDetailDto> getList() {
        return list;
    }

    public void setList(List<CustOrderDetailDto> list) {
        this.list = list;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getAuntId() {
        return auntId;
    }

    public void setAuntId(Integer auntId) {
        this.auntId = auntId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public Integer getEvaluateStar() {
        return evaluateStar;
    }

    public void setEvaluateStar(Integer evaluateStar) {
        this.evaluateStar = evaluateStar;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(Date dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
