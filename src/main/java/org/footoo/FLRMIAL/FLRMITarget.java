/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.FLRMIAL;

import java.io.Serializable;

/**
 * FLRMI�ķ����ѯ��Ŀ�����
 * 
 * @author fengjing.yfj
 * @version $Id: FLRMITarget.java, v 0.1 2014��1��28�� ����10:31:05 fengjing.yfj Exp $
 */
public class FLRMITarget implements Serializable {

    /** ���к� */
    private static final long  serialVersionUID = -5729614448033405490L;

    /** FLRMI ��Э��ǰ׺ */
    public static final String FL_RMI_PREFIX    = "FL_RMI";

    /** ������ */
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
