package cn.dolphinsoft.hilife.authority.service;

import cn.dolphinsoft.hilife.common.domain.PlatformUserInfo;

public interface PlatformUserInfoService {

    /**
     * 
     * Description: 查找账户
     * 
     * @param userId
     */
    PlatformUserInfo find(Integer userId);

    /**
     * 
     * Description: 更新账户
     * 
     * @param dto
     */
    void update(PlatformUserInfo platformUserInfo);

}
