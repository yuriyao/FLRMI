/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.FLRMIALImpl;

import java.util.HashMap;
import java.util.Map;

import org.footoo.FLRMIAL.ServiceCenter;

/**
 * 实现服务中心
 * 
 * @author fengjing.yfj
 * @version $Id: ServiceCenterImpl.java, v 0.1 2014年1月27日 下午5:35:41 fengjing.yfj Exp $
 */
public class ServiceCenterImpl implements ServiceCenter {

    /** 所有的服务 */
    private static final Map<String, Map<Object, Object>> services            = new HashMap<String, Map<Object, Object>>();

    /** 单例 */
    private static final ServiceCenterImpl                SERVICE_CENTER_IMPL = new ServiceCenterImpl();

    /**
     * 默认的构造器
     */
    private ServiceCenterImpl() {
    }

    /**
     * 创建单例
     * 
     * @return
     */
    public static ServiceCenterImpl create() {
        return SERVICE_CENTER_IMPL;
    }

    /** 
     * @see org.footoo.FLRMIAL.ServiceCenter#registerService(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public synchronized void registerService(String prefix, Object mark, Object service) {
        //创建服务前缀对应map
        if (services.get(prefix) == null) {
            services.put(prefix, new HashMap<Object, Object>());
        }
        //保存服务
        services.get(prefix).put(mark, service);
    }

    /** 
     * @see org.footoo.FLRMIAL.ServiceCenter#getService(java.lang.String, java.lang.Object)
     */
    @Override
    public synchronized Object getService(String prefix, Object mark) {
        Map<?, ?> map = services.get(prefix);
        if (map == null) {
            return null;
        }
        return map.get(mark);
    }

    /** 
     * @see org.footoo.FLRMIAL.ServiceCenter#registerServiceIfNotExist(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public synchronized void registerServiceIfNotExist(String prefix, Object mark, Object service) {
        if (services.get(prefix) != null && services.get(prefix).get(mark) != null) {
            registerService(prefix, mark, service);
        }
    }

    /** 
     * @see org.footoo.FLRMIAL.ServiceCenter#unregister(java.lang.String)
     */
    @Override
    public synchronized void unregister(String prefix) {
        services.remove(prefix);
    }

    /** 
     * @see org.footoo.FLRMIAL.ServiceCenter#unregister(java.lang.String, java.lang.Object)
     */
    @Override
    public synchronized void unregister(String prefix, Object mark) {
        if (services.get(prefix) != null) {
            services.get(prefix).remove(mark);
        }
    }

}
