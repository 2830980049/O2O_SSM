package com.edu.o2o.service.impl;

import com.edu.o2o.dao.ShopDao;
import com.edu.o2o.dto.ImageHolder;
import com.edu.o2o.dto.ShopExecution;
import com.edu.o2o.entity.Shop;
import com.edu.o2o.enums.ShopStateEnum;
import com.edu.o2o.service.ShopService;
import com.edu.o2o.service.exceptions.ShopOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.ImageUtil;
import util.PageCalculator;
import util.PathUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/24 0:10
 */
@Service("ShopServiceImpl")
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Transactional //事务标签
    public ShopExecution addShop(Shop shop, ImageHolder imageHolder) throws ShopOperationException{
        if (shop == null) {
            if(shop.getArea() == null)
                return new ShopExecution((ShopStateEnum.NULL_SHOPAREA));
                else if(shop.getShopCategory() == null)
                    return new ShopExecution(ShopStateEnum.NULL_SHOPCATEGORY);
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try{
            //给店铺信息赋初始值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0){
                //让事务终止并回滚
                throw new ShopOperationException("店铺创建失败");
            }
            else{
                if (imageHolder.getImage() != null){
                    //存储图片
                    try {
                        addShopImg(shop, imageHolder);
                    }catch (Exception e){
                        throw new ShopOperationException("addShopImg error:"+e.getMessage());
                    }
                    System.out.println(shop);
                    //更新店铺的图片地址
                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0){
                        //让事务终止并回滚
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }
            }
        }catch (Exception e){
            //让事务终止并回滚
            throw new ShopOperationException("addShop error:"+e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    private void addShopImg(Shop shop, ImageHolder imageHolder) {
        //获取shop图片目录的相对子路径
        String dest_1 = PathUtil.getImgBasePath();
        String dest_2 = dest_1+PathUtil.getShopImagePath(shop.getShopId());
        System.out.println("dest "+dest_2);
        System.out.println("shopImgInputStream " + imageHolder.getImage());
        try {
            //// 文件路径 D:/image/N/666.hpg
            String shopImgAddr = ImageUtil.generateThumbnail(imageHolder.getImage(), dest_2,imageHolder.getImageName());
            shop.setShopImg(shopImgAddr);
            System.out.println("shopImgAddr "+shopImgAddr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    public ShopExecution updateShop(Shop shop, ImageHolder imageHolder) throws ShopOperationException {
        if (shop == null || shop.getShopId() == null)
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        else {
            try {
                // 1 判断是否处理图片
                if (imageHolder.getImage() != null && imageHolder.getImageName() != null && !"".equals(imageHolder.getImageName())) {
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    if (tempShop.getShopImg() != null)
                        ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                    addShopImg(shop, imageHolder);
                }
                // 2 更新店铺信息
                shop.setLastEditTime(new Date());
                int num = shopDao.updateShop(shop);
                if (num <= 0)
                    return new ShopExecution((ShopStateEnum.INNER_ERROR));
                else {
                    shop = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS, shop);
                }
            } catch (Exception e) {
                throw new ShopOperationException("updateShop: " + e.getMessage());
            }
        }
    }

    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex,pageSize);
        List<Shop> shopList = shopDao.queryShopList(shopCondition,rowIndex,pageSize);
        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution se = new ShopExecution();
        if (shopList != null){
            se.setShopList(shopList);
            se.setCount(count);
        }
        else{
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return se;
    }


}
