package com.edu.o2o.dao;

import com.edu.o2o.entity.ProductImg;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/7/1 11:30
 */
@Repository("ProductImgDao")
public interface ProductImgDao {

    /**
     *  批量添加商品详情图片
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    List<ProductImg> queryProductImgList(long productId);

    void deleteProductImgByProductId(long productId);
}
