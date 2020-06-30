package com.edu.o2o.dto;

/**
 *  封装json对象，所有返回结果都使用它
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/30 19:34
 */
public class Results<T> {
    private boolean success; //是否成功标志
    private T data; //成功时返回数据
    private String errorMsg; //错误信息
    private int errorCode;

    public Results() {
    }

    //成功时的构造器
    public Results(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    //错误时的构造器
    public Results(boolean success, String errorMsg, int errorCode) {
        this.success = success;
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
