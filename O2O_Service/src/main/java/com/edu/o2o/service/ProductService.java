package com.edu.o2o.service;

import com.edu.o2o.dto.ImageHolder;
import com.edu.o2o.dto.ProductExecution;
import com.edu.o2o.entity.Product;
import com.edu.o2o.service.exceptions.ProductOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/7/1 12:49
 */
public interface ProductService {

    /**
     *  添加商品信息以及图片处理  缩略图 详情图
     * @return
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImageList) throws ProductOperationException, IOException;

    /**
     * 查询商品列表并分页，可输入的条件有： 商品名（模糊），商品状态，店铺Id,商品类别
     *
     * @param productCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);

    /**
     * 通过商品Id查询唯一的商品信息
     *
     * @param productId
     * @return
     */
    Product getProductById(long productId);

    /**
     * 修改商品信息以及图片处理
     *
     * @param product
     * @param thumbnail
     * @param productImgHolderList
     * @return
     * @throws ProductOperationException
     */
    ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
            throws ProductOperationException, IOException;
}
