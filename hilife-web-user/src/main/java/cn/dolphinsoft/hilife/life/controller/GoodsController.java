package cn.dolphinsoft.hilife.life.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dolphinsoft.hilife.common.util.HiLifeUtil;
import cn.dolphinsoft.hilife.life.dto.ProductSearchDto;
import cn.dolphinsoft.hilife.life.service.GoodsService;
import cn.dolphinsoft.hilife.product.dto.ProductDto;

@Controller
@RequestMapping(value = "/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String goodsIndex(Integer onsaleType, String searchStr, Integer typeId, String sortBy, String order,
            Model model) throws UnsupportedEncodingException {
        ProductSearchDto dto = new ProductSearchDto();
        if (onsaleType != null && !onsaleType.equals(null)) {
            model.addAttribute("onsaleType", onsaleType);
            dto.setOnsaleType(onsaleType);
        }
        if (HiLifeUtil.isNotEmpty(searchStr)) {
            searchStr = URLDecoder.decode(searchStr, "utf-8");
            model.addAttribute("searchStr", searchStr);
            dto.setSearchStr(searchStr);
        }
        if (typeId != null && !typeId.equals(null)) {
            model.addAttribute("productType", goodsService.getTypeName(typeId));
            model.addAttribute("typeId", typeId);
            dto.setTypeId(typeId);
        }
        if (HiLifeUtil.isNotEmpty(sortBy) && HiLifeUtil.isNotEmpty(order)) {
            dto.setSortBy(sortBy);
            dto.setOrder(order);
        }
        goodsService.searchProduct(dto);
        model.addAttribute("product", dto.getList());
        return "goods/index";
    }

    @RequestMapping(value = "/detail/{productId}", method = RequestMethod.GET)
    public String goodsDetail(@PathVariable Integer productId, Model model) {
        goodsService.productHasReviewed(productId);
        ProductDto dto = goodsService.findByProductId(productId);
        model.addAttribute("product", dto);
        return "goods/detail";
    }

    @RequestMapping(value = "/shopcart", method = RequestMethod.GET)
    public String shopcart(Model model) {
        return "goods/shopcart";
    }

    @RequestMapping(value = "/searchProduct", method = RequestMethod.POST)
    @ResponseBody
    public Object searchProduct(@RequestBody ProductSearchDto dto) {
        goodsService.searchProduct(dto);
        return dto;
    }

}
