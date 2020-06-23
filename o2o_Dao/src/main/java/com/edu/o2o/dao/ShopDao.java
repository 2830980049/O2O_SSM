package com.edu.o2o.dao;

import com.edu.o2o.entity.Shop;
import org.springframework.stereotype.Repository;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/23 15:33
 */
@Repository("ShopDao")
public interface ShopDao {
    /**
     * 新增店铺
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
