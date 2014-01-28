/**
 * 
 */
package org.footoo.FLRMI;

import java.io.Externalizable;
import java.lang.reflect.Method;

/**
 * rmi����ͨѶ����Ԫ
 * �������˽���ͨ��ʱ���ݵ���С��Ԫ
 * ������ڷ������õ�ʱ��target��method��params��Ӧ���ܹ����з������õ���С��Ԫ
 * ����ǻ�ȡִ�н����
 * 
 * @author fengjing.yfj
 *
 */
public interface MessageMeta extends Externalizable {

    /**
     * ��ȡ��Ҫ���õķ���
     * 
     * @return
     */
    Method getMethod();

    /**
     * ��ȡ����
     * 
     * @return
     */
    Object[] getParams();

    /**
     * ��ȡ��Ҫ���õĶ���
     * 
     * @return
     */
    Object getTarget();

    /**
     * ���÷���
     * 
     * @param method
     */
    void setMethod(Method method);

    /**
     * ���ò���
     * 
     * @param objs
     */
    void setParams(Object[] objs);

    /**
     * ����Ŀ�����
     * 
     * @param target
     */
    void setTarget(Object target);

}
