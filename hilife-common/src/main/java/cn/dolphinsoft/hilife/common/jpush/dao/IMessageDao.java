package cn.dolphinsoft.hilife.common.jpush.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.dolphinsoft.hilife.common.jpush.entity.MessageEntity;

public interface IMessageDao extends JpaRepository<MessageEntity, Integer> {

}
