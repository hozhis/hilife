package cn.dolphinsoft.hilife.authority.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dolphinsoft.hilife.authority.dto.PlatformUserInfoDto;
import cn.dolphinsoft.hilife.authority.dto.upstream.PlatformUserInfoDetailDto;
import cn.dolphinsoft.hilife.authority.dto.upstream.PlatformUserInfoSearchDto;
import cn.dolphinsoft.hilife.authority.service.PlatformUserInfoService;
import cn.dolphinsoft.hilife.authority.service.UserManagerService;
import cn.dolphinsoft.hilife.common.authority.AuthorityContext;
import cn.dolphinsoft.hilife.common.converter.ConverterService;
import cn.dolphinsoft.hilife.common.domain.PlatformUserInfo;
import cn.dolphinsoft.hilife.common.dto.ResultDto;
import cn.dolphinsoft.hilife.common.dto.ResultDtoFactory;
import cn.dolphinsoft.hilife.common.util.EncryptUtil;
import cn.dolphinsoft.hilife.common.util.HiLifeUtil;

/**
 * Class Name: UserManageController
 * 
 * Description: 用户管理
 * 
 * @author hozhis
 *
 */
@Controller
public class UserManagerController {

    @Autowired
    private UserManagerService userManagerService;

    @Autowired
    private PlatformUserInfoService platformUserInfoService;

    /**
     * 
     * Description: render a home page
     *
     * @param token
     * @param model
     * @return
     */
    @RequestMapping(value = "/userManager")
    public String home(@RequestParam String token, Model model) {

        return "accountMgt/accountList";
    }

    /**
     * 
     * Description: search a user
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/userManager/search")
    @ResponseBody
    public PlatformUserInfoSearchDto search(@RequestBody PlatformUserInfoSearchDto dto) {
        userManagerService.search(dto);
        return dto;
    }

    /**
     * 
     * Description: render getAddpage
     *
     * @param token
     * @param model
     * @return
     */
    @RequestMapping(value = "/userManager/add")
    public String getAddPage(@RequestParam String token, Model model) {
        // 查询所有的角色
        model.addAttribute("roleInfoList", userManagerService.findPlatformRoleInfo());

        return "accountMgt/accountAdd";
    }

    /**
     * 
     * Description: save a user
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/userManager/save")
    @ResponseBody
    public ResultDto<String> save(@RequestBody PlatformUserInfoDetailDto dto) {

        ResultDto<String> msg = userManagerService.saveAndupdate(dto);

        return msg;
    }

    /**
     * 
     * Description: 跳转至修改页面
     *
     * @param userId
     * @param token
     * @param model
     * @return
     */
    @RequestMapping(value = "/userManager/toUpdate/{userId}")
    public String toUpdate(@PathVariable("userId") Integer userId, @RequestParam String token, Model model) {

        model.addAttribute("userId", userId);
        // 所有的角色信息
        model.addAttribute("roleInfoList", userManagerService.findPlatformRoleInfo());
        // 查询用户信息
        model.addAttribute("userInfo", userManagerService.findPlatformUserInfo(userId));

        return "accountMgt/accountAdd";
    }

    /**
     * 
     * Description: update a user
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/userManager/getUpdate")
    @ResponseBody
    public ResultDto<String> update(@RequestBody PlatformUserInfoDetailDto dto) {

        ResultDto<String> msg = userManagerService.saveAndupdate(dto);
        AuthorityContext.clearUserInfoCache(dto.getToken());
        AuthorityContext.clearAuthcCache(dto.getToken());
        return msg;
    }

    /**
     * 
     * Description: 注销用户
     *
     * @param userId
     * @param token
     * @return
     */
    @RequestMapping(value = "/userManager/logout/{userId}")
    @ResponseBody
    public ResultDto<String> logout(@PathVariable("userId") Integer userId, @RequestParam String token) {

        userManagerService.logout(userId);

        return ResultDtoFactory.toAck("注销成功!");
    }

    /**
     * 
     * Description: 查看某个用户
     *
     * @param userId
     * @param token
     * @param model
     * @return
     */
    @RequestMapping(value = "/userManager/detail/{userId}")
    public String detail(@PathVariable("userId") Integer userId, @RequestParam String token, Model model) {
        // 所有的角色信息
        model.addAttribute("roleInfoList", userManagerService.findPlatformRoleInfo());
        // 查询用户信息
        model.addAttribute("userInfo", userManagerService.findPlatformUserInfo(userId));

        return "accountMgt/accountAdd";
    }

    /**
     * 
     * Description: 修改账户
     */
    @RequestMapping(value = "/userManager/modifyAccount")
    public String modifyAccout(@RequestParam String token, Model model) {
        PlatformUserInfo sp = (PlatformUserInfo) AuthorityContext.getCurrentUser();
        model.addAttribute("userInfo", sp);
        return "accountMgt/accountAdd";
    }

    /**
     * 
     * Description: 更新账户
     */
    @RequestMapping(value = "/userManager/updateAccount")
    @ResponseBody
    public ResultDto<String> updateAccout(@RequestBody PlatformUserInfoDto platformUserInfoDto) {
        PlatformUserInfo platformUserInfo = ConverterService.convert(platformUserInfoDto, PlatformUserInfo.class);
        PlatformUserInfo userInfo = platformUserInfoService.find(platformUserInfoDto.getUserId());
        String args = "0";
        // 进行判断，如果新密码为null,则不进行改变
        if (HiLifeUtil.isEmpty(platformUserInfoDto.getNewPwd())) {
            platformUserInfo.setPassword(userInfo.getPassword());
        } else {
            // 对新密码进行加密
            if ((EncryptUtil.encryptMd5(platformUserInfoDto.getOldPwd(), platformUserInfo.getLoginId()))
                    .equals(userInfo.getPassword())) {
                platformUserInfo.setPassword(
                        EncryptUtil.encryptMd5(platformUserInfoDto.getNewPwd(), platformUserInfo.getLoginId()));
                args = "1";
            } else {
                return ResultDtoFactory.toNack("原密码不正确!");
            }

        }
        platformUserInfoService.update(platformUserInfo);
        AuthorityContext.clearUserInfoCache(platformUserInfoDto.getToken());
        AuthorityContext.clearAuthcCache(platformUserInfoDto.getToken());
        return ResultDtoFactory.toAck("保存成功！", args);
    }

    /**
     * Description: 获取用户名
     * 
     * @return
     * 
     */
    @RequestMapping(value = "/userManager/userName", method = RequestMethod.GET)
    @ResponseBody
    public ResultDto<String> getUserName(@RequestParam String token) {
        PlatformUserInfo platformUserInfo = (PlatformUserInfo) AuthorityContext.getCurrentUser();
        return ResultDtoFactory.toAck("", platformUserInfo.getUserName());
    }

}
