/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.FLRMIAL;

/**
 * ������target��ȡ��Ӧ�ķ���
 * 
 * @author fengjing.yfj
 * @version $Id: ServiceFinder.java, v 0.1 2014��1��28�� ����10:27:25 fengjing.yfj Exp $
 */
public interface ServiceFinder {
    /**
     * �Ƿ���Դ������target
     * 
     * @param target Ŀ�����
     * @return �ܹ������ͷ���true�����򷵻�false
     */
    public boolean accept(Object target);

    /**
     * ��ȡ����ֻ���ܹ�accept�ķ��񣬲Ż�����������
     * 
     * @param target Ŀ�����
     * @return �������
     */
    public Object getService(Object target);
}
