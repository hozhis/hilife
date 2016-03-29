package cn.dolphinsoft.hilife.authority.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dolphinsoft.hilife.authority.constant.TreeNodeBean;
import cn.dolphinsoft.hilife.authority.dto.upstream.PlatformRoleInfoSaveAndUpdateDto;
import cn.dolphinsoft.hilife.authority.dto.upstream.PlatformRoleInfoSearchDto;
import cn.dolphinsoft.hilife.authority.service.PlatformUserRoleService;
import cn.dolphinsoft.hilife.common.dto.ResultDto;
import cn.dolphinsoft.hilife.common.dto.ResultDtoFactory;

/**
 * Class Name: UserRoleController Description: 角色管理控制器
 * 
 * @author hozhis
 *
 */
@Controller
public class RoleInfoController {

    @Autowired
    private PlatformUserRoleService platformUserRoleService;

    /**
     * 
     * Description: render home page
     *
     * @return
     */
    @RequestMapping(value = "/platformRole")
    public String home(@RequestParam String token, Model model) {

        return "roleMgt/roleList";
    }

    /**
     * Description: search a userRole
     * 
     * @param dto
     * @return
     */
    @RequestMapping(value = "/platformRole/search")
    @ResponseBody
    public PlatformRoleInfoSearchDto search(@RequestBody PlatformRoleInfoSearchDto dto) {

        platformUserRoleService.search(dto);

        return dto;
    }

    /**
     * 
     * Description: render add page
     *
     * @return
     */
    @RequestMapping(value = "/platformRole/add")
    public String getAddpage(@RequestParam String token, Model model) {
        // 获取到所有的子节点
        model.addAttribute("functionTreeList", platformUserRoleService.getFunctionTree(null));

        return "roleMgt/roleAdd";
    }

    /**
     * 
     * Description: save a platformRole
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/platformRole/save")
    @ResponseBody
    public ResultDto<String> save(@RequestBody PlatformRoleInfoSaveAndUpdateDto dto) {

        ResultDto<String> msg = platformUserRoleService.saveAndupdate(dto);
        return msg;
    }

    /**
     * 
     * Description: 跳转至修改页面。
     *
     * @return
     */
    @RequestMapping(value = "/platformRole/toUpdate/{roleId}")
    public String toUpdate(@PathVariable(value = "roleId") Integer roleId, @RequestParam String token, Model model) {

        model.addAttribute("roleId", roleId);
        PlatformRoleInfoSaveAndUpdateDto dto = platformUserRoleService.findPlatformById(roleId);
        model.addAttribute("roleInfo", dto);
        List<TreeNodeBean> treeNodeList = platformUserRoleService.getFunctionTree(null);
        model.addAttribute("functionTreeList", treeNodeList);
        return "roleMgt/roleAdd";
    }

    /**
     * 
     * Description: 更新
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/platformRole/getUpdate")
    @ResponseBody
    public ResultDto<String> update(@RequestBody PlatformRoleInfoSaveAndUpdateDto dto) {

        ResultDto<String> msg = platformUserRoleService.saveAndupdate(dto);

        return msg;
    }

    /**
     * 
     * Description: 作废
     *
     * @param roleId
     * @param token
     * @return
     */
    @RequestMapping(value = "/platformRole/logout/{roleId}")
    @ResponseBody
    public ResultDto<String> logout(@PathVariable(value = "roleId") Integer roleId, @RequestParam String token) {
        platformUserRoleService.logout(roleId);
        return ResultDtoFactory.toAck("注销成功!");
    }

    /**
     * 
     * Description: 查看
     * 
     * @param roleId
     * @param token
     * @param model
     * @return
     */
    @RequestMapping(value = "/platformRole/detail/{roleId}")
    public String detail(@PathVariable(value = "roleId") Integer roleId, @RequestParam String token, Model model) {

        model.addAttribute("roleInfo", platformUserRoleService.findPlatformById(roleId));
        model.addAttribute("functionTreeList", platformUserRoleService.getFunctionTree(null));
        return "roleMgt/roleAdd";
    }

}
