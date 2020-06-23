package com.edu.o2o.enums;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/23 20:58
 */
// 枚举
public enum ShopStateEnum {
    CHECK(0, "审核中"),
    OFFLINE(-1,"非法店铺"),
    SUCCESS(1, "操作成功"),
    PASS(2, "通过认证"),
    INNER_ERROR(-1001,"内部系统错误"),
    NULL_SHOPID(-1002,"ShopId为空"),
    NULL_SHOP(-1003,"Shop信息为空"),
    NULL_SHOPAREA(-1004,"ShopArea为空"),
    NULL_SHOPCATEGORY(-1005,"ShopCategory为空");
    private int state;
    private String stateInfo;

    /**
     *  依据传入的state返回相应的enum值
     * @param state
     * @return
     */
    public static ShopStateEnum stateOf(int state){
        for (ShopStateEnum stateEnum:values()){
            if (stateEnum.getState() == state)
                return stateEnum;
        }
        return null;
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

    private ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }


}
