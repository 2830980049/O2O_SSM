package com.edu.o2o.service;

import com.edu.o2o.dto.ProductCategoryExecution;
import com.edu.o2o.entity.ProductCategory;
import com.edu.o2o.enums.ProductCategoryStateEnum;
import com.edu.o2o.service.exceptions.ProductCategoryOperationException;

import java.util.List;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/30 17:29
 */
public interface ProductCategoryService {
    /**
     * 查找指定某个店铺下所有商品类别信息
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryList(long shopId);

    /**
     *  批量添加
     * @param productCategoryList
     * @return
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException;

    /**
     *  将此类别下的商品里的类别id置为空 再删除掉该商品类别
     * @param productCategoryId
     * @param shopId
     * @return
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException;
}
