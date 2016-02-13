package cn.dolphinsoft.hilife.common.jpush.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class Name: MessageEntity
 * 
 * Description: TODO (表未建)消息实体
 * 
 * @author hozhis
 *
 */
@Entity
@Table(name = "MESSAGE")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MESSAGE_ID")
    private Long messageId;

    @Column(name = "FROM_TYPE")
    private String fromType;

    @Column(name = "TO_TYPE")
    private String toType;

    @Column(name = "FROM_ID")
    private Integer fromId;

    @Column(name = "TO_ID")
    private Integer toId;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "PARAM")
    private String param;

    @Column(name = "READ_STATUS")
    private String readStatus;

    @Column(name = "CREATE_DATE")
    private Date creatDate;

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getFromType() {
        return fromType;
    }

    public void setFromType(String fromType) {
        this.fromType = fromType;
    }

    public String getToType() {
        return toType;
    }

    public void setToType(String toType) {
        this.toType = toType;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

}
