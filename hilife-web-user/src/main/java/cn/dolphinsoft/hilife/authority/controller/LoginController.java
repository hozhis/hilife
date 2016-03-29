package cn.dolphinsoft.hilife.authority.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import cn.dolphinsoft.hilife.authority.dto.LoginDto;
import cn.dolphinsoft.hilife.authority.service.LoginService;
import cn.dolphinsoft.hilife.common.authority.AuthorityContext;
import cn.dolphinsoft.hilife.common.dto.ResultDto;
import cn.dolphinsoft.hilife.common.dto.ResultDtoFactory;

/**
 * Class Name: LoginController Description: 登陆验证Controller
 * 
 * @author hozhis
 *
 */
@Api(value = "login-controller", description = "登陆相关接口", position = 1)
@RequestMapping(value = "/auth")
@Controller
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "发送登陆验证短信", notes = "洪志胜 已完成")
    @RequestMapping(value = "/sendLoginSms", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto<String> sendLoginSms(@RequestBody LoginDto loginDto) {
        Assert.notNull(loginDto.getLoginId());
        loginService.sendLoginSms(loginDto.getLoginId());
        return ResultDtoFactory.toAck("短信发送成功");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login/login";
    }

    @ApiOperation(value = "登陆认证", notes = "洪志胜 已完成")
    @RequestMapping(value = "/login/authc", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto<LoginDto> login(@RequestBody LoginDto loginDto) {
        String tokenStr = null;
        try {
            tokenStr = AuthorityContext.login(loginDto.getLoginId(), loginDto.getPassword());
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return ResultDtoFactory.toNack("登录失败");
        }
        loginDto.setToken(tokenStr);
        return ResultDtoFactory.toAck("登录成功", loginDto);
    }

    @ApiOperation(value = "注销", notes = "洪志胜 已完成")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto<String> logout(@RequestBody LoginDto loginDto) {
        AuthorityContext.logout(loginDto.getToken());
        loginService.clearToken(loginDto.getToken());
        return ResultDtoFactory.toAck("登出成功");
    }

    @RequestMapping(value = "/login/QA", method = RequestMethod.GET)
    public String QA(Model model) {
        return "login/loginQA";
    }

    @RequestMapping(value = "/checkUser", method = RequestMethod.GET)
    @ResponseBody
    public ResultDto<String> checkUserExist(@RequestParam String loginId) {
        return loginService.checkUser(loginId);
    }
}
