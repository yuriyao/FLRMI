/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.FLRMIAL;

/**
 * �������ģ����ڷ����ע��Ͳ���
 * ��������������ɣ�����ǰ׺�������־�Լ�ʵ�ʵķ���
 * 
 * @author fengjing.yfj
 * @version $Id: ServiceCenter.java, v 0.1 2014��1��27�� ����5:20:30 fengjing.yfj Exp $
 */
public interface ServiceCenter {
    /**
     * ע�����
     * 
     * @param prefix ����ǰ׺
     * @param mark �����־
     * @param service ʵ�ʵķ���
     */
    void registerService(String prefix, Object mark, Object service);

    /**
     * ������񲻴��ھͲŽ���ע�ᣬ���Ḳ��ԭ�ȵķ���
     * 
     * @param prefix
     * @param mark
     * @param Service
     */
    void registerServiceIfNotExist(String prefix, Object mark, Object Service);

    /**
     * ��ȡ���񣬲��ҷ���Ĺ��̱�����ǰ׺��mark����ͬ
     * 
     * @param prefix
     * @param mark
     * @return
     */
    Object getService(String prefix, Object mark);

    /**
     * ע�����з���ǰ׺Ϊprefix�ķ���
     * 
     * @param prefix
     */
    void unregister(String prefix);

    /**
     * ע��ǰ׺Ϊprefix����־Ϊmark�ķ���
     * 
     * @param prefix
     * @param mark
     */
    void unregister(String prefix, Object mark);
}
