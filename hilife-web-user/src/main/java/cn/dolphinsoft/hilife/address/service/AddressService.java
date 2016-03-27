package cn.dolphinsoft.hilife.address.service;

import java.util.List;

import cn.dolphinsoft.hilife.address.dto.CustAddressDto;
import cn.dolphinsoft.hilife.common.dto.ResultDto;

/**
 * 
 * Class Name: AddressService
 * 
 * Description: 收获地址服务
 * 
 * @author hozhis
 *
 */
public interface AddressService {

    /**
     * 
     * Description: 获取收获地址列表
     *
     * @param token
     * @return
     */
    List<CustAddressDto> getAllAddress(String token);

    /**
     * 
     * Description: 获取用户下单选择地址
     *
     * @param token
     * @return
     */
    String getServiceAddress(String token);

    /**
     * 
     * Description: 设置用户下单选择地址
     *
     * @param token
     * @param addressId
     */
    void setServiceAddress(String token, Integer addressId);

    /**
     * 
     * Description: 设置默认服务地址
     *
     * @param token
     * @param addressId
     */
    ResultDto<String> setDefaultAddress(String token, Integer addressId);

    /**
     * 
     * Description: 删除服务地址
     *
     * @param addressId
     */
    void delete(Integer addressId);

    /**
     * 
     * Description: 根据addressId获取地址详细信息
     *
     * @param addressId
     * @return
     */
    CustAddressDto getAddressDetail(Integer addressId);
}
