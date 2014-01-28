/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.FLRMIAL;

import java.io.Serializable;

/**
 * FLRMI的服务查询的目标对象
 * 
 * @author fengjing.yfj
 * @version $Id: FLRMITarget.java, v 0.1 2014年1月28日 上午10:31:05 fengjing.yfj Exp $
 */
public class FLRMITarget implements Serializable {

    /** 序列号 */
    private static final long  serialVersionUID = -5729614448033405490L;

    /** FLRMI 的协议前缀 */
    public static final String FL_RMI_PREFIX    = "FL_RMI";

    /** 服务名 */
    private String             serviceName;

    /**
     * Getter method for property <tt>serviceName</tt>.
     * 
     * @return property value of serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Setter method for property <tt>serviceName</tt>.
     * 
     * @param serviceName value to be assigned to property serviceName
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

}
