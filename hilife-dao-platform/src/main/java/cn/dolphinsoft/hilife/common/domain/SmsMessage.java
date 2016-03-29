package cn.dolphinsoft.hilife.common.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SMS_MESSAGE")
public class SmsMessage implements Serializable {

    private static final long serialVersionUID = 5040119381328324484L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sms_id")
    private Integer smsId;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "status")
    private String status;

    @Column(name = "message")
    private String message;

    @Column(name = "create_date")
    private Date createDate;

    public Integer getSmsId() {
        return smsId;
    }

    public void setSmsId(Integer smsId) {
        this.smsId = smsId;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
