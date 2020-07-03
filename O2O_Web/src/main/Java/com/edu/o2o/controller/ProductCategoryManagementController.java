package com.edu.o2o.controller;

import com.edu.o2o.dto.ProductCategoryExecution;
import com.edu.o2o.dto.Results;
import com.edu.o2o.entity.ProductCategory;
import com.edu.o2o.entity.Shop;
import com.edu.o2o.enums.ProductCategoryStateEnum;
import com.edu.o2o.service.ProductCategoryService;
import com.edu.o2o.service.exceptions.ProductCategoryOperationException;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import util.HttpServletRequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/30 18:29
 */
@Controller
@RequestMapping("/shop")
public class ProductCategoryManagementController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
    @ResponseBody
    public Results<List<ProductCategory>> getProductCategoryList(HttpServletRequest request){
        Shop currentShop = (Shop)request.getSession().getAttribute("currentShop");
        List<ProductCategory> list = null;
        System.out.println(currentShop);
        if (currentShop != null && currentShop.getShopId() > 0){
            list = productCategoryService.getProductCategoryList(currentShop.getShopId());
            System.out.println(list);
            return new Results<List<ProductCategory>>(true,list);
        }
        else{
            ProductCategoryStateEnum ps = ProductCategoryStateEnum.INNER_ERROR;
            return new Results<List<ProductCategory>>(false,ps.getStateInfo(),ps.getState());
        }
    }

    /**
     *  批量插入
     * @param productCategoryList
     * @param request
     * @return
     */
    @RequestMapping(value = "/addproductcategory", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addProductCategory(@RequestBody List<ProductCategory> productCategoryList,HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        Shop currentShop = (Shop)request.getSession().getAttribute("currentShop");
        System.out.println(currentShop);
        for (ProductCategory pc: productCategoryList)
            pc.setShopId(currentShop.getShopId());
        if (productCategoryList != null && productCategoryList.size() > 0) {
            try {
                ProductCategoryExecution pe = productCategoryService.batchAddProductCategory(productCategoryList);
                if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState())
                    modelMap.put("success",true);
                else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",pe.getStateInfo());
                }
            }
            catch (ProductCategoryOperationException e) {
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
                return modelMap;
            }
        }
        else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请至少输入一个商品类别");
        }
        return modelMap;
    }

    @RequestMapping(value = "/removeproductcategory", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> removeproductcategory(Long productCategoryId ,HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        if (productCategoryId != null && productCategoryId > 0) {
            try {
                Shop currentShop = (Shop)request.getSession().getAttribute("currentShop");
                ProductCategoryExecution pe = productCategoryService.deleteProductCategory(productCategoryId,currentShop.getShopId());
                if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState())
                    modelMap.put("success",true);
                else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",pe.getStateInfo());
                }
            }
            catch (ProductCategoryOperationException e) {
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
                return modelMap;
            }
        }
        else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请至少选择一个商品类别");
        }
        return modelMap;
    }

}
