package cn.dolphinsoft.hilife.common.enumeration;

public enum OrderStatus {

    All(null, "全部"), RECEIVE(101, "已下单待分配"), DISPATCH(201, "已分配"), SERVICING(301, "服务中"), 
    FINISH(401, "服务完成"), PAY(501,"待支付"), EVALUATE(601, "待评价"), CLOSE(701, "订单完成已关闭"),
    CANCEL(801, "订单已取消");

    private Integer key;

    private String value;

    private OrderStatus(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
