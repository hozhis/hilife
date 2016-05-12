package cn.dolphinsoft.hilife.address.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.dolphinsoft.hilife.address.dto.CustAddressDto;
import cn.dolphinsoft.hilife.address.service.AddressService;
import cn.dolphinsoft.hilife.common.authority.AuthorityContext;
import cn.dolphinsoft.hilife.common.converter.ConverterService;
import cn.dolphinsoft.hilife.common.domain.CustAddress;
import cn.dolphinsoft.hilife.common.domain.CustUserInfo;
import cn.dolphinsoft.hilife.common.dto.ResultDto;
import cn.dolphinsoft.hilife.common.dto.ResultDtoFactory;
import cn.dolphinsoft.hilife.common.repository.ICustAddressRepository;
import cn.dolphinsoft.hilife.common.repository.ICustUserInfoRepository;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private ICustUserInfoRepository userInfoRepository;

    @Autowired
    private ICustAddressRepository addressRepository;

    @Override
    public List<CustAddressDto> getAllAddress(String token) {
        Integer userId = userInfoRepository.findByToken(token).getUserId();
        Assert.notNull(userId);
        List<CustAddress> custAddressList = addressRepository.findByUserId(userId);
        List<CustAddressDto> dtos = new ArrayList<CustAddressDto>();
        for (CustAddress custAddress : custAddressList) {
            CustAddressDto dto = new CustAddressDto();
            dto.setAddressId(custAddress.getAddressId());
            dto.setAddressName(custAddress.getAddressName());
            dto.setDef(custAddress.getDef());
            dto.setUserId(custAddress.getUserId());
            dto.setConsignee(custAddress.getConsignee());
            dto.setPhone(custAddress.getPhone());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public String getServiceAddress(String token) {
        CustUserInfo userInfo = userInfoRepository.findByToken(token);
        Assert.notNull(userInfo);
        CustAddress custAddress = addressRepository.findByAddressId(userInfo.getAddressId());
        if (custAddress != null) {
            return custAddress.getAddressName();
        } else {
            return "";
        }
    }

    @Transactional
    @Override
    public void setServiceAddress(String token, Integer addressId) {
        userInfoRepository.saveServiceAddress(token, addressId);
    }

    @Transactional
    @Override
    public ResultDto<String> setDefaultAddress(String token, Integer addressId) {
        Integer userId = userInfoRepository.findByToken(token).getUserId();
        CustAddress custAddress = addressRepository.findByAddressIdAndDef(addressId, 1);
        if (custAddress != null) {
            addressRepository.clearDefault(userId);
            return ResultDtoFactory.toAck("操作成功");
        }
        addressRepository.clearDefault(userId);
        addressRepository.setDefault(addressId);
        userInfoRepository.saveServiceAddress(token, addressId);
        return ResultDtoFactory.toAck("操作成功");
    }

    @Transactional
    @Override
    public void delete(Integer addressId) {
        addressRepository.delete(addressId);
    }

    @Override
    public CustAddressDto getAddressDetail(Integer addressId) {
        CustAddress custAddress = addressRepository.findByAddressId(addressId);
        Assert.notNull(custAddress);
        return ConverterService.convert(custAddress, CustAddressDto.class);
    }

    @Override
    public ResultDto<String> saveAddress(CustAddressDto dto) {
        dto.setUserId(AuthorityContext.getCurrentUser().getUserId());
        CustAddress address = ConverterService.convert(dto, CustAddress.class);
        addressRepository.save(address);
        return ResultDtoFactory.toAck("保存成功");
    }

}
