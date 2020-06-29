package com.edu.o2o.controller;

import checkers.oigj.quals.O;
import checkers.units.quals.A;
import com.edu.o2o.dto.ShopExecution;
import com.edu.o2o.entity.Area;
import com.edu.o2o.entity.PersonInfo;
import com.edu.o2o.entity.Shop;
import com.edu.o2o.entity.ShopCategory;
import com.edu.o2o.enums.ShopStateEnum;
import com.edu.o2o.service.AreaService;
import com.edu.o2o.service.ShopCategoryService;
import com.edu.o2o.service.ShopService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import sun.dc.pr.PRError;
import util.CodeUtil;
import util.HttpServletRequestUtil;
import util.ImageUtil;
import util.PathUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/28 11:48
 */
@Controller
@RequestMapping("/shopadmin")
public class ShopManagerController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;


    @RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getshopinitinfo(){
        Map<String,Object> modelMap = new HashMap<String, Object>();
        List<ShopCategory> ShopCategoryList = new ArrayList<ShopCategory>();
        List<Area> areas = new ArrayList<Area>();
        try {
            ShopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areas = areaService.getAreal();
            modelMap.put("ShopCategoryList", ShopCategoryList);
            modelMap.put("areaList", areas);
            modelMap.put("success", true);
        }
        catch (Exception e){
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }

    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> registerShop(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();

        if (!CodeUtil.check_code(request)){
            modelMap.put("success",false);
            modelMap.put("errMsg", "验证码错误！");
            return modelMap;
        }
        // 1. 接收并转化相应参数，包括店铺信息以及图片信息
        String shopStr = HttpServletRequestUtil.getString(request,"shopStr");
        // JSON对象
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            // JSON 转化为实体类
            shop = mapper.readValue(shopStr,Shop.class);
        }
        catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile shopImg = null;
        // 文化解析
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        // 判断request是否有文件流
        if (commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
            // 获取前端文件
            shopImg = (CommonsMultipartFile)multipartHttpServletRequest.getFile("shopImg");
        }
        else{
            modelMap.put("success",false);
            modelMap.put("errMsg", "上传图片不能为空");
            return modelMap;
        }
        System.out.println(shop);
        // 2. 注册店铺
        if (shop != null && shopImg != null){
            PersonInfo owner = new PersonInfo();
            owner.setUserId(1L);
            shop.setPersonInfo(owner);
            // getOriginalFilename 获取 文件名字
            ShopExecution se = null;
            try {
                se = shopService.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
                if (se.getState() == ShopStateEnum.CHECK.getState())
                    modelMap.put("success",true);
                else{
                    modelMap.put("success",false);
                    modelMap.put("errMsg", se.getStateInfo());
                }
            } catch (IOException e) {
                modelMap.put("success",false);
                modelMap.put("errMsg", e.getMessage());
            }
            return modelMap;
        }
        else {
            modelMap.put("success",false);
            modelMap.put("errMsg", "请输入店铺信息");
            return modelMap;
        }
    }

//    public static void inputStreamToFile(InputStream inputStream, File file){
//       // 输出流
//        FileOutputStream os = null;
//        try {
//            os = new FileOutputStream(file);
//            int bytesRead = 0;
//            byte[] buffer = new byte[1024];
//            // 读取 inputStream 并写入 os
//            while ((bytesRead = inputStream.read(buffer)) != -1){
//                os.write(buffer,0,bytesRead);
//            }
//        }
//        catch (Exception e){
//            throw new RuntimeException("调用inputStreamToFile产生异常："+e.getMessage());
//        }
//        finally {
//            try {
//                if (os != null)
//                    os.close();
//                if (inputStream != null)
//                    inputStream.close();
//            }
//            catch (Exception e){
//                throw new RuntimeException("调用inputStreamToFile关闭IO产生异常："+e.getMessage());
//            }
//        }
//    }


}
