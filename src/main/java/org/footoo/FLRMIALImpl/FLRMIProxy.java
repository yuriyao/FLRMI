/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.FLRMIALImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.footoo.FLRMI.FLRMIException;

/**
 * 客户端的代理
 * 
 * @author fengjing.yfj
 * @version $Id: FLRMIProxy.java, v 0.1 2014年1月28日 下午1:45:05 fengjing.yfj Exp $
 */
public class FLRMIProxy implements InvocationHandler {

    /** 目标对象 */
    private Object      target;

    /** 客户端 */
    private FLRMIClient client;

    /**
     * 默认的构造器
     */
    public FLRMIProxy() {

    }

    /**
     * 构造器
     * 
     * @param target
     */
    public FLRMIProxy(Object target) {
        this.target = target;
    }

    /**
     * 构造器
     * 
     * @param target
     * @param client
     */
    public FLRMIProxy(Object target, FLRMIClient client) {
        this.target = target;
        this.client = client;
    }

    /** 
     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //参数检查
        if (client == null) {
            throw new FLRMIException("还没有设置客户端，没有办法进行连接");
        }

        //调用服务端
        return client.invoke(target, method, args, -1);
    }

    /**
     * Getter method for property <tt>target</tt>.
     * 
     * @return property value of target
     */
    public Object getTarget() {
        return target;
    }

    /**
     * Setter method for property <tt>target</tt>.
     * 
     * @param target value to be assigned to property target
     */
    public void setTarget(Object target) {
        this.target = target;
    }

    /**
     * Getter method for property <tt>client</tt>.
     * 
     * @return property value of client
     */
    public FLRMIClient getClient() {
        return client;
    }

    /**
     * Setter method for property <tt>client</tt>.
     * 
     * @param client value to be assigned to property client
     */
    public void setClient(FLRMIClient client) {
        this.client = client;
    }

}
