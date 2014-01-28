/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.FLRMIALImpl;

import org.footoo.FLRMIAL.FLRMITarget;
import org.footoo.FLRMIAL.ServiceFinder;
import org.footoo.FLRMIAL.TargetFinderCenter;

/**
 * ���ڴ�FLRMIЭ��Ŀ���ȡ�������
 * 
 * @author fengjing.yfj
 * @version $Id: FLRMIServiceFinder.java, v 0.1 2014��1��28�� ����10:38:15 fengjing.yfj Exp $
 */
public class FLRMIServiceFinder implements ServiceFinder {

    /** ��̬���� */
    private static final FLRMIServiceFinder FLRMI_SERVICE_FINDER = new FLRMIServiceFinder();

    //ע�ᵽ��������
    static {
        TargetFinderCenter.registerFinder(FLRMI_SERVICE_FINDER);
    }

    /** Ĭ�Ϲ����� */
    private FLRMIServiceFinder() {

    }

    /**
     * ��������
     * 
     * @return
     */
    public static FLRMIServiceFinder create() {
        return FLRMI_SERVICE_FINDER;
    }

    /** 
     * @see org.footoo.FLRMIAL.ServiceFinder#accept(java.lang.Object)
     */
    @Override
    public boolean accept(Object target) {
        //ֻ����FLRMI����
        if (target != null && target instanceof FLRMITarget) {
            return true;
        }
        return false;
    }

    /** 
     * @see org.footoo.FLRMIAL.ServiceFinder#getService(java.lang.Object)
     */
    @Override
    public Object getService(Object target) {
        FLRMITarget target2 = (FLRMITarget) target;

        /** ��ȡ���� */
        return ServiceCenterImpl.create().getService(FLRMITarget.FL_RMI_PREFIX,
            target2.getServiceName());
    }

}
