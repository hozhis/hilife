package cn.dolphinsoft.hilife.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.dolphinsoft.hilife.common.domain.CustInviteUser;

public interface ICustInviteUserRepository extends JpaRepository<CustInviteUser, Integer> {

    CustInviteUser findByUserId(Integer userId);

}
