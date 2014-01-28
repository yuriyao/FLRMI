/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.FLRMI;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ���л�����ע������
 * 
 * @author fengjing.yfj
 * @version $Id: FLRMISerizableCenter.java, v 0.1 2014��1��27�� ����10:52:03 fengjing.yfj Exp $
 */
public abstract class FLRMISerizableCenter {
    /** ����ע������л����� */
    private static final Map<Integer, SerizableMessageMeta> serizables = new ConcurrentHashMap<Integer, SerizableMessageMeta>();

    /**
     * ע����Ԫ�����л��ĺ���
     * 
     * @param smm ���л�����
     * @param override ������ͬ��mark���Ƿ���и���
     */
    public static synchronized void registerSerizableMessageMeta(SerizableMessageMeta smm,
                                                                 boolean override) {
        if (smm == null) {
            throw new FLRMIException("���л���Ԫ����ע���ָ��");
        }
        //����Ƿ��Ѿ�������ͬ����Ԫ
        if (!override) {
            if (serizables.get(smm.getMark()) != null) {
                throw new FLRMIException("���л���Ԫ�����Ѿ�����");
            }
        }
        //����ע��
        serizables.put(smm.getMark(), smm);
    }

    /**
     * ���ø��ǵķ���ע����Ԫ���л�����
     * 
     * @param smm ��Ԫ���л�����
     */
    public static void registerSerizableMessageMeta(SerizableMessageMeta smm) {
        registerSerizableMessageMeta(smm, true);
    }

    /**
     * ֻ�е�������ָ�������л����߲Ż����ע��
     * 
     * @param smm ��Ԫ���л�����
     */
    public static synchronized void registerSerizableMessageMetaIfNotExist(SerizableMessageMeta smm) {
        if (smm == null) {
            throw new FLRMIException("���л���Ԫ����ע���ָ��");
        }
        if (serizables.get(smm.getMark()) == null) {
            serizables.put(smm.getMark(), smm);
        }
    }

    /**
     * ��ȡָ��mark�����л���Ԫ����
     * 
     * @param mark ��Ԫ���л��ı�־
     * @return
     */
    public static synchronized SerizableMessageMeta getSerizableMessageMeta(Integer mark) {
        return serizables.get(mark);
    }
}
