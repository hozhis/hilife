package cn.dolphinsoft.hilife.common.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * Class Name: CustOrder
 * 
 * Description: 订单表Entity
 * 
 * @author hozhis
 *
 */
@Entity
@Table(name = "CUST_ORDER")
public class CustOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Integer orderId;

    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "ORDER_TYPE")
    private Integer orderType; // 1 - 服务订单， 0 - 商品订单

    @Column(name = "TOTAL_AMOUNT")
    private Integer totalAmount;

    @Column(name = "AUNT_ID")
    private Integer auntId;

    @Column(name = "ORDER_STATUS")
    private Integer orderStatus;

    @Column(name = "SERVICE_ADDRESS")
    private String serviceAddress;

    @Column(name = "EVALUATE_STAR")
    private Integer evaluateStar;

    @Column(name = "EVALUATE_CONTENT")
    private String evaluateContent;

    @Column(name = "RECEIVE_TIME")
    private Date receiveTime;

    @Column(name = "DISPATCH_TIME")
    private Date dispatchTime;

    @Column(name = "PAY_TIME")
    private Date payTime;

    @Column(name = "EVALUATE_TIME")
    private Date evaluateTime;

    @Column(name = "CLOSE_TIME")
    private Date closeTime;

    @Column(name = "CANCEL_TIME")
    private Date cancelTime;

    @Column(name = "FINISH_TIME")
    private Date finishTime;

    @Column(name = "STATUS")
    private String status;

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
