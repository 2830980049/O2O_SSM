package com.edu.o2o.dao;

import com.edu.o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/30 17:22
 */
@Repository("ProductCategoryDao")
public interface ProductCategoryDao {
    /**
     * 通过shop id 查询店铺商品类别
     * @param shopId
     * @return
     */
    List<ProductCategory> queryProductCategoryList(long shopId);

    /**
     * 批量插入 商品类别
     * @param productCategoryList
     * @return
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);

    /**
     *  删除商品类别
     * @param productCategoryId
     * @return
     */
    int deleteProductCategory(@Param("productCategoryId") long productCategoryId,@Param("shopId") long shopId);
}
