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

import com.thoughtworks.xstream.XStream;

/**
 * xml��ʽ�������л��Ĺ���
 * ���ַ���ʵ�ֵ���ò�Ҫ����ʵ��Ӧ���У�ֻҪ���ڲ���
 * 
 * @author fengjing.yfj
 * @version $Id: XMLSerizableMessageMetaImpl.java, v 0.1 2014��1��27�� ����3:24:49 fengjing.yfj Exp $
 */
public class XMLSerizableMessageMetaImpl extends AbstractSerizableMessageMeta {
    /** mark */
    private static final Integer                     MARK                            = 0x1234123;

    /** ���� */
    private static final XMLSerizableMessageMetaImpl XML_SERIZABLE_MESSAGE_META_IMPL = new XMLSerizableMessageMetaImpl();

    /** xml���л����� */
    private final XStream                            stream                          = new XStream();

    //������ע�ᵽע������
    static {
        FLRMISerizableCenter
            .registerSerizableMessageMetaIfNotExist(XML_SERIZABLE_MESSAGE_META_IMPL);
    }

    /**
     * Ĭ�ϵĹ��캯��
     */
    private XMLSerizableMessageMetaImpl() {

    }

    /**
     * ��������
     * 
     * @return
     */
    public static XMLSerizableMessageMetaImpl create() {
        return XML_SERIZABLE_MESSAGE_META_IMPL;
    }

    /** 
     * @see org.footoo.FLRMI.SerizableMessageMeta#getMark()
     */
    @Override
    public Integer getMark() {
        return MARK;
    }

    /** 
     * @see org.footoo.FLRMIImpl.AbstractSerizableMessageMeta#writeTarget(java.io.ObjectOutput, java.lang.Object)
     */
    @Override
    protected void writeTarget(ObjectOutput output, Object target) {
        try {
            output.writeObject(stream.toXML(target));
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
            output.writeObject(stream.toXML(method));
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
            output.writeObject(stream.toXML(params));
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
            return stream.fromXML((String) input.readObject());
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
            return (Method) stream.fromXML((String) input.readObject());
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
            return (Object[]) stream.fromXML((String) input.readObject());
        } catch (Exception e) {
            e.printStackTrace();
            throw new FLRMIException("�޷���ȡ��������");
        }
    }

}
