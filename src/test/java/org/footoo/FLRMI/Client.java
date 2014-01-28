/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.FLRMI;

import org.footoo.FLRMIALImpl.FLRMI;

/**
 * 
 * @author fengjing.yfj
 * @version $Id: Client.java, v 0.1 2014年1月28日 下午2:22:20 fengjing.yfj Exp $
 */
public class Client {
    public static void main(String args[]) {
        HelloWorld helloWorld = (HelloWorld) FLRMI.getFLRMIService("hello", "localhost", 1223,
            new Class[] { HelloWorld.class });
        System.out.println(helloWorld.hello("jeff"));
    }
}
