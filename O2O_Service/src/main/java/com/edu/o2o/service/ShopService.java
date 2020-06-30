package com.edu.o2o.service;

import com.edu.o2o.dto.ShopExecution;
import com.edu.o2o.entity.Shop;
import com.edu.o2o.service.exceptions.ShopOperationException;

import java.io.File;
import java.io.InputStream;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/24 0:09
 */
public interface ShopService {
    /**
     *  注册店铺信息，包括图片处理
     *  inputStream 无法获取文件名字
     * @param shop
     * @param shopInputStream
     * @param fileName
     * @return
     */
    ShopExecution addShop(Shop shop, InputStream shopInputStream,String fileName) throws ShopOperationException;

    /**
     * 通过店铺ID获取店铺信息
     * @param shopId
     * @return
     */
    Shop getByShopId(long shopId);

    /**
     * 更新店铺信息，包括对图片处理
     * @param shop
     * @param shopInputStream
     * @param fileName
     * @return
     */
    ShopExecution updateShop(Shop shop,InputStream shopInputStream,String fileName) throws ShopOperationException;

    /**
     *  根据ShopCondition分页返回相应店铺列表
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);
}