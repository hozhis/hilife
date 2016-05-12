package cn.dolphinsoft.hilife.me.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import cn.dolphinsoft.hilife.common.dto.ResultDto;
import cn.dolphinsoft.hilife.me.dto.CustUserInfoDto;

public interface MeService {

    CustUserInfoDto getUserInfo();

    void saveUserinfo(CustUserInfoDto dto);

    Map<String, Map<Integer, String>> getCitys();

    ResultDto<String> uploadImage(MultipartFile file);

}
