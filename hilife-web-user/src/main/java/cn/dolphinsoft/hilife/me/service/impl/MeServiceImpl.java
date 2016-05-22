package cn.dolphinsoft.hilife.me.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import cn.dolphinsoft.hilife.common.authority.AuthorityContext;
import cn.dolphinsoft.hilife.common.constant.BasicTypeConstant;
import cn.dolphinsoft.hilife.common.converter.ConverterService;
import cn.dolphinsoft.hilife.common.domain.BasicPara;
import cn.dolphinsoft.hilife.common.domain.CustUserInfo;
import cn.dolphinsoft.hilife.common.domain.Region;
import cn.dolphinsoft.hilife.common.dto.ResultDto;
import cn.dolphinsoft.hilife.common.dto.ResultDtoFactory;
import cn.dolphinsoft.hilife.common.repository.IBasicParaRepository;
import cn.dolphinsoft.hilife.common.repository.ICustUserInfoRepository;
import cn.dolphinsoft.hilife.common.repository.IRegionRepository;
import cn.dolphinsoft.hilife.common.util.FtpUtil;
import cn.dolphinsoft.hilife.me.dto.CustUserInfoDto;
import cn.dolphinsoft.hilife.me.service.MeService;

@Service
public class MeServiceImpl implements MeService {

    @Autowired
    private ICustUserInfoRepository userInfoRepository;

    @Autowired
    private IRegionRepository regionRepository;

    @Autowired
    private IBasicParaRepository basicParaRepository;

    private static final String IMAGE = "/HILIFE/HL_USER";

    private static final String IMAGEPATH = "/USER_AVATARS";

    @Override
    public CustUserInfoDto getUserInfo() {
        CustUserInfo custUserInfo = userInfoRepository.findByToken(AuthorityContext.getCurrentToken());
        Assert.notNull(custUserInfo);
        CustUserInfoDto dto = ConverterService.convert(custUserInfo, CustUserInfoDto.class);
        String ftpSettingPlatform = "";
        List<BasicPara> basicParas = basicParaRepository.findByTypeId(BasicTypeConstant.FTP_SETTING_PLATFORM);
        if (basicParas != null && basicParas.size() > 0) {
            ftpSettingPlatform = basicParas.get(0).getParaValue1();
        }
        if (dto.getCustImage() != null) {
            dto.setCustImage(ftpSettingPlatform + dto.getCustImage());
        }
        Region region = regionRepository.findByRegionId(custUserInfo.getRegionId());
        if (region != null) {
            Integer parentId = region.getParentId();
            dto.setRegion(regionRepository.findByRegionId(parentId).getAreaname() + "," + region.getAreaname());
        }
        return dto;
    }

    @Transactional
    @Override
    public void saveUserinfo(CustUserInfoDto dto) {
        userInfoRepository.saveUserinfo(dto.getUsername(), dto.getSex(), dto.getRegionId(), dto.getToken());
    }

    @Override
    public Map<String, Map<Integer, String>> getCitys() {
        Map<String, Map<Integer, String>> map = new HashMap<>();
        List<Region> regions = regionRepository.findByLevel(1);// Level 1 - 省份
        for (Region region : regions) {
            List<Region> citys = regionRepository.findByParentId(region.getRegionId());
            Map<Integer, String> cMap = new HashMap<>();
            for (Region city : citys) {
                cMap.put(city.getRegionId(), city.getAreaname());
                map.put(region.getAreaname(), cMap);
            }
        }
        return map;
    }

    @Transactional
    @Override
    public ResultDto<String> uploadImage(MultipartFile file) {
        if (file.isEmpty()) {
            return ResultDtoFactory.toNack("图片不能为空，请上传图片！");
        }
        if (file.getSize() > 2100000) {
            return ResultDtoFactory.toNack("仅支持上传2M以下的图片，请重新上传！");
        }
        String[] string = file.getOriginalFilename().split("\\.");
        String srcFileName = new Date().getTime() + "." + (string[string.length - 1]).toLowerCase();
        try {
            if (file.getInputStream() != null) {
                InputStream inputStream = file.getInputStream();
                FtpUtil.upload(srcFileName, inputStream, IMAGE, IMAGEPATH);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String custImage = IMAGE + IMAGEPATH + "/" + srcFileName;
        userInfoRepository.updateImage(custImage, AuthorityContext.getCurrentToken());
        return ResultDtoFactory.toAck("上传成功");
    }

}
