package cn.dolphinsoft.hilife.authority.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dolphinsoft.hilife.authority.dto.LoginDto;
import cn.dolphinsoft.hilife.authority.dto.upstream.PlatformLoginUpdatePwDto;
import cn.dolphinsoft.hilife.authority.service.PlatformLoginService;
import cn.dolphinsoft.hilife.common.authority.AuthorityContext;
import cn.dolphinsoft.hilife.common.domain.PlatformUserInfo;
import cn.dolphinsoft.hilife.common.dto.ResultDto;
import cn.dolphinsoft.hilife.common.dto.ResultDtoFactory;
import cn.dolphinsoft.hilife.common.enumeration.BaseStatus;
import cn.dolphinsoft.hilife.common.repository.PlatformUserInfoRepository;
import cn.dolphinsoft.hilife.common.security.KaptchaSupport;
import cn.dolphinsoft.hilife.common.util.HiLifeUtil;

/**
 * Class Name: LoginController
 * 
 * Description: 平台登陆Controller
 * 
 * @author hozhis
 *
 */
@Controller
@RequestMapping(value = "/auth")
public class LoginController {

    @Autowired
    private KaptchaSupport kaptchaSupport;

    @Autowired
    private PlatformLoginService loginService;

    @Autowired
    private PlatformUserInfoRepository platformUserInfoRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String productInfoView(Model model) {
        return "login/login";
    }

    /*
     * @RequestMapping("/validate") public void validate() { }
     */

    @RequestMapping(value = "/login/authc", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestBody LoginDto loginDto) {
        if (!kaptchaSupport.validateCaptcha(loginDto.getCaptcha(), loginDto.getKey())) {
            return new ResultDto<String>("other", "验证码错误", null);
        }
        PlatformUserInfo userInfo = platformUserInfoRepository.findByLoginId(loginDto.getLoginId(),
                BaseStatus.EFFECT.getKey());
        if (userInfo == null) {
            return ResultDtoFactory.toNack("用户名不存在", null);
        }
        String tokenStr = null;
        try {
            tokenStr = AuthorityContext.login(loginDto.getLoginId(), loginDto.getPassword());
        } catch (UnknownAccountException e) {
            return ResultDtoFactory.toNack("用户名不存在", null);
        } catch (CredentialsException e) {
            return ResultDtoFactory.toNack("用户名或密码错误", null);
        }
        loginDto.setToken(tokenStr);
        return ResultDtoFactory.toAck("登陆成功", loginDto);
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "index/index";
    }

    /**
     * Description: render captcha
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void captcha(@RequestParam String key, HttpServletResponse response) throws ServletException, IOException {
        kaptchaSupport.captcha(key, response);
    }

    /**
     * 
     * Description: 退出
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto<String> logout(@RequestParam String token) {
        loginService.clearToken(AuthorityContext.getCurrentUser().getUserId());
        AuthorityContext.logout(token);
        return ResultDtoFactory.toAck("");
    }

    /**
     * 
     * Description: 跳转至忘记密码
     *
     * @return
     */
    @RequestMapping(value = "/forget", method = RequestMethod.GET)
    public String forgetPw() {
        return "login/forget";
    }

    /**
     * 
     * Description: get code 获取验证码
     *
     * @param phone
     * @return
     */
    @RequestMapping(value = "/getCode", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto<String> getCode(@RequestBody PlatformLoginUpdatePwDto dto) {
        Long phone = dto.getPhone();
        if (HiLifeUtil.isEmpty(String.valueOf(phone))) {
            return ResultDtoFactory.toNack("请输入手机号码！");
        }
        PlatformUserInfo info = platformUserInfoRepository.findByPhone(phone, BaseStatus.EFFECT.getKey());
        if (info == null || BaseStatus.INVALID.getKey().equals(info.getStatus())) {
            return ResultDtoFactory.toNack("账户不存在！");
        }
        String catpcha = loginService.getCatpchaByLoginId(phone);
        loginService.saveMessage(catpcha, phone);
        return ResultDtoFactory.toAck("");
    }

    /**
     * 
     * Description: get code 验证验证码
     *
     * @param phone
     * @return
     */
    @RequestMapping(value = "/checkCode", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto<String> checkCode(@RequestBody PlatformLoginUpdatePwDto dto) {
        String catpcha = loginService.getCatpchaByLoginId(dto.getPhone());
        if (catpcha.equals(dto.getCode())) {
            return ResultDtoFactory.toAck("");
        }
        return ResultDtoFactory.toNack("验证码错误！");
    }

    /**
     * 
     * Description: 修改密码
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/submitPw", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto<String> submit(@RequestBody PlatformLoginUpdatePwDto dto) {
        ResultDto<String> msg = loginService.updatePw(dto);
        return msg;
    }
}
