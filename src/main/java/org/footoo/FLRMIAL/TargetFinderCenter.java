/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.FLRMIAL;

import java.util.ArrayList;
import java.util.List;

/**
 * Ŀ������������ע������
 * 
 * @author fengjing.yfj
 * @version $Id: TargetFinderCenter.java, v 0.1 2014��1��28�� ����10:41:40 fengjing.yfj Exp $
 */
public abstract class TargetFinderCenter {
    /** ���еķ�������� */
    private static final List<ServiceFinder> finders           = new ArrayList<ServiceFinder>();

    /** ���õķ�������� */
    private static final String              INTENAL_FINDERS[] = { "org.footoo.FLRMIALImpl.FLRMIServiceFinder" };

    //ע�����е����õĲ�����
    static {
        for (String string : INTENAL_FINDERS) {
            try {
                Class.forName(string);
            } catch (Exception e) {
                //�����쳣
            }
        }
    }

    /**
     * ע���������� 
     * 
     * @param finder
     */
    public static synchronized void registerFinder(ServiceFinder finder) {
        if (!finders.contains(finder)) {
            finders.add(finder);
        }
    }

    /**
     * ע�����������
     * 
     * @param finder ������
     */
    public static synchronized void unregisterFinder(ServiceFinder finder) {
        finders.remove(finder);
    }

    /**
     * ����Ŀ�������Ҷ�Ӧ�ķ��������
     * 
     * @param target Ŀ�����
     * @return ��ȡ��Ӧ�ķ��������
     */
    public static synchronized ServiceFinder getFinder(Object target) {
        for (ServiceFinder finder : finders) {
            if (finder.accept(target)) {
                return finder;
            }
        }
        return null;
    }

}
