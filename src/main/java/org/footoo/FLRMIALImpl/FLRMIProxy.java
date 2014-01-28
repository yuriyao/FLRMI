/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.FLRMIALImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.footoo.FLRMI.FLRMIException;

/**
 * �ͻ��˵Ĵ���
 * 
 * @author fengjing.yfj
 * @version $Id: FLRMIProxy.java, v 0.1 2014��1��28�� ����1:45:05 fengjing.yfj Exp $
 */
public class FLRMIProxy implements InvocationHandler {

    /** Ŀ����� */
    private Object      target;

    /** �ͻ��� */
    private FLRMIClient client;

    /**
     * Ĭ�ϵĹ�����
     */
    public FLRMIProxy() {

    }

    /**
     * ������
     * 
     * @param target
     */
    public FLRMIProxy(Object target) {
        this.target = target;
    }

    /**
     * ������
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
        //�������
        if (client == null) {
            throw new FLRMIException("��û�����ÿͻ��ˣ�û�а취��������");
        }

        //���÷����
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
