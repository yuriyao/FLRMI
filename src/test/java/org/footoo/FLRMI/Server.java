/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.FLRMI;

import java.io.IOException;

import org.footoo.FLRMIALImpl.FLRMI;
import org.footoo.FLRMIALImpl.FLRMIServer;

/**
 * 服务端测试
 * 
 * @author fengjing.yfj
 * @version $Id: Server.java, v 0.1 2014年1月28日 下午2:14:59 fengjing.yfj Exp $
 */
public class Server {
    public static void main(String args[]) throws IOException {
        HelloWorld helloWorld = new HelloWorldImpl();
        FLRMI.registerFLRMIService("hello", helloWorld);
        FLRMI.startServer(new FLRMIServer());
        System.out.println("启动成功");
    }
}
