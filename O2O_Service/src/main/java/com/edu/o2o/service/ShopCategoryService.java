package com.edu.o2o.service;

import com.edu.o2o.entity.ShopCategory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/28 22:13
 */
public interface ShopCategoryService {
    List<ShopCategory > getShopCategoryList(ShopCategory shopCategoryCondition);
}
