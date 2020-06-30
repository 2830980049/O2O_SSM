package com.edu.o2o.service.impl;

import com.edu.o2o.dao.ProductCategoryDao;
import com.edu.o2o.dto.ProductCategoryExecution;
import com.edu.o2o.entity.ProductCategory;
import com.edu.o2o.enums.ProductCategoryStateEnum;
import com.edu.o2o.service.ProductCategoryService;
import com.edu.o2o.service.exceptions.ProductCategoryOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/30 17:30
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    /**
     * 通过shop id查询店铺商品类别
     * @param shopId
     * @return
     */
    @Override
    public List<ProductCategory> getProductCategoryList(long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }

    @Override
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        if (productCategoryList != null && productCategoryList.size() > 0) {
            try {
                int num = productCategoryDao.batchInsertProductCategory(productCategoryList);
                if (num <= 0)
                    throw new ProductCategoryOperationException("店铺类别创建失败");
                else
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            } catch (Exception e) {
                throw new ProductCategoryOperationException("batchAddProductCategory error: " + e.getMessage());
            }
        }
        else
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
    }

    /**
     *  删除商品类别
     * @param productCategoryId
     * @param shopId
     * @return
     * @throws ProductCategoryOperationException
     */
    @Override
    @Transactional //事务
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException {
        // 将此商品类别下的商品类别ID置为空
        try {
            int num = productCategoryDao.deleteProductCategory(productCategoryId,shopId);
            if(num <= 0)
                throw new ProductCategoryOperationException("商品类别删除失败");
            else
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
        }
        catch (Exception e){
            throw new ProductCategoryOperationException("deleteProductCategory error: "+e.getMessage());
        }
    }


}
