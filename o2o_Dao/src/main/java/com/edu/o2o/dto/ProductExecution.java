package com.edu.o2o.dto;

import com.edu.o2o.entity.Product;
import com.edu.o2o.enums.ProductStateEnum;

import java.util.List;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/7/1 12:49
 */
public class ProductExecution {
    // 结果状态
    private int state;
    // 状态标识
    private String stateInfo;

    // 商品数量
    private int count;

    // 操作的product （增删改查商品 使用）
    private Product product;

    // 获取product列表 (查询商品列表 使用)
    private List<Product> productList;

    public ProductExecution() {
    }

    // 失败构造器
    public ProductExecution(ProductStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    // 成功构造器
    public ProductExecution(ProductStateEnum stateEnum, Product product) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.product = product;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
