/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.FLRMIImpl;

import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Method;

import org.footoo.FLRMI.FLRMIException;
import org.footoo.FLRMI.FLRMISerizableCenter;

/**
 * �򵥵����л���Ԫ����
 * 
 * @author fengjing.yfj
 * @version $Id: SimpleSerizableMessageMetaImpl.java, v 0.1 2014��1��27�� ����3:01:10 fengjing.yfj Exp $
 */
public class SimpleSerizableMessageMetaImpl extends AbstractSerizableMessageMeta {
    /** mark,ÿһ�����л���Ԫ����������Ψһ�� */
    private static final Integer                        MARK   = 0x199205;

    /** ���ڵ��� */
    private static final SimpleSerizableMessageMetaImpl simple = new SimpleSerizableMessageMetaImpl();

    //ע�����л�������ע������
    static {
        FLRMISerizableCenter.registerSerizableMessageMetaIfNotExist(simple);
    }

    /**
     * ����һ����������
     * 
     * @return ��������
     */
    public static SimpleSerizableMessageMetaImpl create() {
        return simple;
    }

    /**
     * Ĭ�Ϲ��캯����˽�л���ʵ�ֵ���
     */
    private SimpleSerizableMessageMetaImpl() {

    }

    /** 
     * @see org.footoo.FLRMI.SerizableMessageMeta#getMark()
     */
    @Override
    public Integer getMark() {
        return MARK;
    }

    /** 
     * 
     * @see org.footoo.FLRMIImpl.AbstractSerizableMessageMeta#writeTarget(java.io.ObjectOutput, java.lang.Object)
     */
    @Override
    protected void writeTarget(ObjectOutput output, Object target) {
        try {
            output.writeObject(target);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FLRMIException("�޷����л�Ŀ�����[" + target + "]");
        }
    }

    /** 
     * @see org.footoo.FLRMIImpl.AbstractSerizableMessageMeta#writeMethod(java.io.ObjectOutput, java.lang.reflect.Method)
     */
    @Override
    protected void writeMethod(ObjectOutput output, Method method) {

        try {
            //����Ϊ��
            if (method == null) {
                output.writeObject(method);
                return;
            }
            //
            output.writeObject(method.getName());
            output.writeObject(method.getParameterTypes());
            output.writeObject(method.getDeclaringClass());
        } catch (Exception e) {
            e.printStackTrace();
            throw new FLRMIException("�޷����л���������[" + method + "]");
        }
    }

    /** 
     * @see org.footoo.FLRMIImpl.AbstractSerizableMessageMeta#writeParams(java.io.ObjectOutput, java.lang.Object[])
     */
    @Override
    protected void writeParams(ObjectOutput output, Object[] params) {
        try {
            output.writeObject(params);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FLRMIException("�޷����л���������[" + params + "]");
        }
    }

    /** 
     * @see org.footoo.FLRMIImpl.AbstractSerizableMessageMeta#readTarget(java.io.ObjectInput)
     */
    @Override
    protected Object readTarget(ObjectInput input) {
        try {
            return input.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new FLRMIException("�޷���ȡĿ�����");
        }
    }

    /** 
     * @see org.footoo.FLRMIImpl.AbstractSerizableMessageMeta#readMethod(java.io.ObjectInput)
     */
    @Override
    protected Method readMethod(ObjectInput input) {
        try {
            //return (Method) input.readObject();
            Object object = input.readObject();
            if (object == null) {
                return null;
            }
            //��ȡ������
            String name = (String) object;
            //��ȡ�����б�
            Class<?>[] params = (Class<?>[]) input.readObject();
            //��ȡ��
            Class<?> clz = (Class<?>) input.readObject();
            //��÷���
            return clz.getDeclaredMethod(name, params);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FLRMIException("�޷���ȡ��������");
        }
    }

    /** 
     * @see org.footoo.FLRMIImpl.AbstractSerizableMessageMeta#readParams(java.io.ObjectInput)
     */
    @Override
    protected Object[] readParams(ObjectInput input) {
        try {
            return (Object[]) input.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new FLRMIException("�޷���ȡ��������");
        }
    }

}
