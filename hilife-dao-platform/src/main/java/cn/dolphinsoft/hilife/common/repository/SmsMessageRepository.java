package cn.dolphinsoft.hilife.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.dolphinsoft.hilife.common.domain.SmsMessage;

public interface SmsMessageRepository
        extends JpaRepository<SmsMessage, Integer>, JpaSpecificationExecutor<SmsMessage> {

}
