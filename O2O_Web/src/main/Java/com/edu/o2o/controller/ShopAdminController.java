package com.edu.o2o.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/28 14:30
 */
@Controller
@RequestMapping(value = "shop", method = RequestMethod.GET)
public class ShopAdminController {
    @RequestMapping(value = "shop_add")
    public String shopAdd(){
        return "shop/shop_add_update";
    }

    @RequestMapping(value = "/shoplist")
    public String shopList(){
        return "shop/shop_list";
    }

    @RequestMapping(value = "/shopmanagement")
    public String shopmanagement(){
        return "shop/shopmanagement";
    }

    @RequestMapping(value = "/productcategorymanagement")
    public String productcategorymanagement(){
        return "shop/productcategorymanagement";
    }

}
