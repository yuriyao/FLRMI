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
 * xml方式进行序列化的工具
 * 这种方法实现的最好不要用于实际应用中，只要用于测试
 * 
 * @author fengjing.yfj
 * @version $Id: XMLSerizableMessageMetaImpl.java, v 0.1 2014年1月27日 下午3:24:49 fengjing.yfj Exp $
 */
public class XMLSerizableMessageMetaImpl extends AbstractSerizableMessageMeta {
    /** mark */
    private static final Integer                     MARK                            = 0x1234123;

    /** 单例 */
    private static final XMLSerizableMessageMetaImpl XML_SERIZABLE_MESSAGE_META_IMPL = new XMLSerizableMessageMetaImpl();

    /** xml序列化工具 */
    private final XStream                            stream                          = new XStream();

    //将工具注册到注册中心
    static {
        FLRMISerizableCenter
            .registerSerizableMessageMetaIfNotExist(XML_SERIZABLE_MESSAGE_META_IMPL);
    }

    /**
     * 默认的构造函数
     */
    private XMLSerizableMessageMetaImpl() {

    }

    /**
     * 创建单例
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
            throw new FLRMIException("无法序列化目标对象[" + target + "]");
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
            throw new FLRMIException("无法序列化方法对象[" + method + "]");
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
            throw new FLRMIException("无法序列化参数对象[" + params + "]");
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
            throw new FLRMIException("无法读取目标对象");
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
            throw new FLRMIException("无法读取方法对象");
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
            throw new FLRMIException("无法读取参数对象");
        }
    }

}
