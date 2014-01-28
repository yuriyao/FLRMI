/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.FLRMI;

/**
 * 
 * @author fengjing.yfj
 * @version $Id: HelloWorldImpl.java, v 0.1 2014年1月28日 下午2:16:23 fengjing.yfj Exp $
 */
public class HelloWorldImpl implements HelloWorld {

    /**  */
    private static final long serialVersionUID = -8229738828935464460L;

    /** 
     * @see org.footoo.FLRMI.HelloWorld#hello(java.lang.String)
     */
    @Override
    public String hello(String name) {
        return "Hello " + name;
    }

}
