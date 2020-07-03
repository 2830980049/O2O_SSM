package com.edu.o2o.service.exceptions;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/7/1 12:55
 */
public class ProductOperationException extends RuntimeException{
    public ProductOperationException(String msg) {
        super(msg);
    }
}
