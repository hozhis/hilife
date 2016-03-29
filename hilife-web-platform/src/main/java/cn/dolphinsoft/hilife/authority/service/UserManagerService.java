package cn.dolphinsoft.hilife.authority.service;

import java.util.List;

import cn.dolphinsoft.hilife.authority.dto.upstream.PlatformUserInfoDetailDto;
import cn.dolphinsoft.hilife.authority.dto.upstream.PlatformUserInfoSearchDto;
import cn.dolphinsoft.hilife.common.domain.PlatformRoleInfo;
import cn.dolphinsoft.hilife.common.dto.ResultDto;

/**
 * Class Name: UserManagerService Description: 服务层
 * 
 * @author hozhis
 *
 */
public interface UserManagerService {

    /**
     * 
     * Description: search all userInfo by choose
     *
     * @param dto
     */
    void search(PlatformUserInfoSearchDto dto);

    /**
     * 
     * Description: find all RoleInfo
     *
     * @return
     */
    List<PlatformRoleInfo> findPlatformRoleInfo();

    /**
     * 
     * Description: find a userInfo by userId
     *
     * @param userId
     * @return
     */
    PlatformUserInfoDetailDto findPlatformUserInfo(Integer userId);

    /**
     * 
     * Description: save or update a userInfo
     *
     * @param dto
     * @return
     */
    ResultDto<String> saveAndupdate(PlatformUserInfoDetailDto dto);

    /**
     * 
     * Description: logout a userId 注销某个账号
     *
     * @param userId
     */

    void logout(Integer userId);

}
