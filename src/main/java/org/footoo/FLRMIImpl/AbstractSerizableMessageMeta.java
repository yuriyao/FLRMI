/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.FLRMIImpl;

import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Method;

import org.footoo.FLRMI.FLRMIException;
import org.footoo.FLRMI.MessageMeta;
import org.footoo.FLRMI.SerizableMessageMeta;

/**
 * ����һ��ͨ�õ����л���Ԫ�Ĺ���
 * 
 * @author fengjing.yfj
 * @version $Id: AbstractSerizableMessageMeta.java, v 0.1 2014��1��27�� ����11:23:29 fengjing.yfj Exp $
 */
public abstract class AbstractSerizableMessageMeta implements SerizableMessageMeta {

    /** 
     * @see org.footoo.FLRMI.SerizableMessageMeta#write(java.io.ObjectOutput, org.footoo.FLRMI.MessageMeta)
     */
    @Override
    public void write(ObjectOutput output, MessageMeta messageMeta) {
        try {
            //���л���������
            writeTarget(output, messageMeta.getTarget());
            writeMethod(output, messageMeta.getMethod());
            writeParams(output, messageMeta.getParams());
        } catch (FLRMIException e) {
            //�����׳�
            throw e;
        } catch (Exception e) {
            throw new FLRMIException("���л�����ʧ�ܣ����л�����[" + this + "],���л���Ԫ����[" + messageMeta + "]");
        }
    }

    /** 
     * @see org.footoo.FLRMI.SerizableMessageMeta#read(java.io.ObjectInputStream)
     */
    @Override
    public void read(ObjectInput input, MessageMeta messageMeta) {
        //���ݵĲ�������ǿգ���������쳣ֱ����ϵͳ�׳���ָ���쳣

        try {
            messageMeta.setTarget(readTarget(input));
            messageMeta.setMethod(readMethod(input));
            messageMeta.setParams(readParams(input));
        } catch (FLRMIException e) {
            throw e;
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * ���л�target����
     * 
     * @param output ���л���
     * @param target Ŀ��
     */
    protected abstract void writeTarget(ObjectOutput output, Object target);

    /**
     * ���л���Ҫ���õķ���
     * 
     * @param output  ���л���
     * @param method ���õķ���
     */
    protected abstract void writeMethod(ObjectOutput output, Method method);

    /**
     * ���л�����
     * 
     * @param output ���л���
     * @param params ����
     */
    protected abstract void writeParams(ObjectOutput output, Object[] params);

    /**
     * ��ȡĿ�����
     * 
     * @param input ���л�������
     * @return �����л��õ�target
     */
    protected abstract Object readTarget(ObjectInput input);

    /**
     * ��ȡ��������
     * 
     * @param input ���л�������
     * @return �����л��õķ���
     */
    protected abstract Method readMethod(ObjectInput input);

    /**
     * ��ȡ��������
     * 
     * @param input ���л�������
     * @return �����л��õĲ���
     */
    protected abstract Object[] readParams(ObjectInput input);
}
