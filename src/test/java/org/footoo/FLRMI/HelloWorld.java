/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.FLRMI;

import java.io.Serializable;

/**
 * 
 * @author fengjing.yfj
 * @version $Id: HelloWorld.java, v 0.1 2014年1月28日 下午2:15:31 fengjing.yfj Exp $
 */
public interface HelloWorld extends Serializable {
    public String hello(String name);
}
