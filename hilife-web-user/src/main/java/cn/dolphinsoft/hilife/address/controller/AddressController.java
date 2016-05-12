package cn.dolphinsoft.hilife.address.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dolphinsoft.hilife.address.dto.CustAddressDto;
import cn.dolphinsoft.hilife.address.service.AddressService;
import cn.dolphinsoft.hilife.common.dto.ResultDto;
import cn.dolphinsoft.hilife.common.dto.ResultDtoFactory;
import cn.dolphinsoft.hilife.me.dto.CustUserInfoDto;

@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "address/index";
    }

    @RequestMapping(value = "/setServiceAddress", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto<String> setServiceAddress(@RequestBody CustUserInfoDto dto) {
        addressService.setServiceAddress(dto.getToken(), dto.getAddressId());
        return ResultDtoFactory.toAck("success");
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public ResultDto<List<CustAddressDto>> getAddressList(@RequestParam String token) {
        List<CustAddressDto> dtos = addressService.getAllAddress(token);
        return ResultDtoFactory.toAck("获取成功", dtos);
    }

    @RequestMapping(value = "/setDefaultAddress", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto<String> setDefAddress(@RequestBody CustAddressDto dto) {
        return addressService.setDefaultAddress(dto.getToken(), dto.getAddressId());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto<String> delete(@RequestBody CustAddressDto dto) {
        addressService.delete(dto.getAddressId());
        return ResultDtoFactory.toAck("操作成功");
    }

    @RequestMapping(value = "/detail/{addressId}", method = RequestMethod.GET)
    public String addressDetail(@PathVariable Integer addressId, Model model) {
        CustAddressDto dto = addressService.getAddressDetail(addressId);
        model.addAttribute("custAddress", dto);
        return "address/detail";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addAddress(Model model) {
        return "address/detail";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto<String> saveAddress(@RequestBody CustAddressDto dto) {
        return addressService.saveAddress(dto);
    }
}
