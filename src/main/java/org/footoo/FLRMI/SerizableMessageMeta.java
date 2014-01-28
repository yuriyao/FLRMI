/**
 * 
 */
package org.footoo.FLRMI;

import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * ������Ԫ���л�ʱʹ�õ����л�����
 * 
 * @author fengjing.yfj
 *
 */
public interface SerizableMessageMeta {

    /**
     * ��ȡ���л��ı�־
     * 
     * @return
     */
    public Integer getMark();

    /**
     * �������л����������л�mark�����õĻ��Զ��������л�mark
     * 
     * @param output ���л�����
     * @param messageMeta ��Ԫ
     */
    public void write(ObjectOutput output, MessageMeta messageMeta);

    /**
     * �����л���ע�ⷴ���л�mark�Ѿ����
     * 
     * @param input �����л�����
     * @return �����л��õ���Ԫ
     */
    public void read(ObjectInput input, MessageMeta messageMeta);
}
