package cn.dolphinsoft.hilife.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.dolphinsoft.hilife.common.domain.SmsMessage;

/**
 * Class Name: ISmsMessageRepository Description: TODO
 * 
 * @author hozhis
 *
 */
public interface ISmsMessageRepository
        extends JpaRepository<SmsMessage, Integer>, JpaSpecificationExecutor<SmsMessage> {

}
