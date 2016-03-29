package cn.dolphinsoft.hilife.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dolphinsoft.hilife.address.service.AddressService;
import cn.dolphinsoft.hilife.common.authority.AuthorityContext;
import cn.dolphinsoft.hilife.common.dto.ResultDto;
import cn.dolphinsoft.hilife.common.dto.ResultDtoFactory;
import cn.dolphinsoft.hilife.common.util.HiLifeUtil;
import cn.dolphinsoft.hilife.product.dto.ProductDto;
import cn.dolphinsoft.hilife.product.service.ProductService;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService producrService;

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/index")
    public String home(Model model) {
        return "product/index";
    }

    @RequestMapping(value = "/service/{serviceId}")
    public String serviceOrder(@PathVariable("serviceId") String id, Model model) {
        ProductDto dto = producrService.getProductDetail(id);
        if (dto != null) {
            model.addAttribute("serviceName", dto.getProductName());
            model.addAttribute("minHours", dto.getMount());
            model.addAttribute("price", dto.getPrice());
            model.addAttribute("detail", dto.getIntroduction());
            model.addAttribute("remark", dto.getRemark());
            model.addAttribute("logo", dto.getImage());
            model.addAttribute("serviceId", id);
        }
        String serviceAddress = addressService.getServiceAddress(AuthorityContext.getCurrentToken());
        if (!HiLifeUtil.isEmpty(serviceAddress)) {
            model.addAttribute("serviceAddress", serviceAddress);
        }
        return "product/serviceOrder";
    }

    @RequestMapping(value = "/order/submit", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto<String> serviceSubmit(@RequestBody ProductDto dto) {
        return ResultDtoFactory.toAck("");
    }
}
