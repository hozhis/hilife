package cn.dolphinsoft.hilife.common.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.dolphinsoft.hilife.common.enumeration.BaseStatus;

@Entity
@Table(name = "SMS_MESSAGE")
public class SmsMessage implements Serializable {

    private static final long serialVersionUID = 851821233904308854L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SMS_ID")
    private Integer smsId;

    @Column(name = "PHONE")
    private Long phone;

    @Column(name = "STATUS")
    private String status = BaseStatus.EFFECT.getKey();

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "CREATE_DATE")
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
