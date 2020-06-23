package com.edu.o2o.service;

import com.edu.o2o.dto.ShopExecution;
import com.edu.o2o.entity.Shop;

import java.io.File;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/24 0:09
 */
public interface ShopService {
    ShopExecution addShop(Shop shop, File shopImg);
}
