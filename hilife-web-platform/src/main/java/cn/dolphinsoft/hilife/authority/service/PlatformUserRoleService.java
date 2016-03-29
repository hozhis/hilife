package cn.dolphinsoft.hilife.authority.service;

import java.util.List;

import cn.dolphinsoft.hilife.authority.constant.TreeNodeBean;
import cn.dolphinsoft.hilife.authority.dto.upstream.PlatformRoleInfoSaveAndUpdateDto;
import cn.dolphinsoft.hilife.authority.dto.upstream.PlatformRoleInfoSearchDto;
import cn.dolphinsoft.hilife.common.dto.ResultDto;

public interface PlatformUserRoleService {

    /**
     * 
     * Description: search a roleList
     *
     * @param dto
     */
    void search(PlatformRoleInfoSearchDto dto);

    /**
     * 
     * Description: save or update role
     *
     * @param dto
     * @return
     */
    ResultDto<String> saveAndupdate(PlatformRoleInfoSaveAndUpdateDto dto);

    /**
     * 
     * Description: get a functionTree
     *
     * @param functionId
     * @return
     */
    List<TreeNodeBean> getFunctionTree(String functionId);

    /**
     * 
     * Description: logout(作废) a role
     *
     * @param roleId
     */
    void logout(Integer roleId);

    /**
     * 
     * Description: find a role by Id
     *
     * @param roleId
     * @return
     */
    PlatformRoleInfoSaveAndUpdateDto findPlatformById(Integer roleId);

}
