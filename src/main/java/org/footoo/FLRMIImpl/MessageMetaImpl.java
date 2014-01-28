/**
 * 
 */
package org.footoo.FLRMIImpl;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Method;

import org.footoo.FLRMI.FLRMIException;
import org.footoo.FLRMI.FLRMISerizableCenter;
import org.footoo.FLRMI.MessageMeta;
import org.footoo.FLRMI.SerizableMessageMeta;

/**
 * ʵ��һ����Ԫ
 * 
 * @author fengjing.yfj
 *
 */
public class MessageMetaImpl implements MessageMeta {

    /** Ŀ�����,����ʱ������ö��󣬷���ʱ������ֵ���󣬻����׳����쳣���� */
    private Object                target;

    /** �������� */
    private Method                method;

    /** �������� */
    private Object                params[];

    /** ���л����� */
    private SerizableMessageMeta  serizableMessageMeta;

    /** Ĭ�ϵ����л������б� */
    private static final String[] SE_MESSAGE_METAS = new String[] {
            "org.footoo.FLRMIImpl.XMLSerizableMessageMetaImpl",
            "org.footoo.FLRMIImpl.SimpleSerizableMessageMetaImpl" };

    //����Ĭ�ϵ����л�����
    static {
        //�������е����л�����
        //��֤��Щ���л��������Ա�ע�ᵽ���л�ע������
        for (String name : SE_MESSAGE_METAS) {
            try {
                Class.forName(name);
            } catch (ClassNotFoundException e) {
                //�����쳣
            }
        }
    }

    /**
     * Ĭ�ϵĹ��캯��
     */
    public MessageMetaImpl() {
    }

    /**
     * ���캯��
     * 
     * @param serizableMessageMeta ���л�����
     */
    public MessageMetaImpl(SerizableMessageMeta serizableMessageMeta) {
        this.serizableMessageMeta = serizableMessageMeta;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        if (this.serizableMessageMeta == null) {
            this.serizableMessageMeta = SimpleSerizableMessageMetaImpl.create();
        }
        //���mark
        out.writeInt(serizableMessageMeta.getMark());

        //���ʵ�ʵ�����
        serizableMessageMeta.write(out, this);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        //��ȡmark
        Integer mark = in.readInt();
        //��ȡ��Ӧ�ķ����л����ߣ���Ϊ���ڴ����ж�ȡ��ʱ����û�����л������ģ���Ҫ
        //ͨ�����л�������ע�����Ĳ��Ҷ�Ӧ�����л�����
        this.serizableMessageMeta = FLRMISerizableCenter.getSerizableMessageMeta(mark);
        if (this.serizableMessageMeta == null) {
            throw new FLRMIException("�޷���ȡmarkΪ[" + mark.intValue()
                                     + "]�ķ����л�����, ���ֶ�ע�ᵽ���л���������[FLRMISerizableCenter]");
        }
        //��ȡmark��Ӧ��
        serizableMessageMeta.read(in, this);
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getParams() {
        return params;
    }

    @Override
    public Object getTarget() {
        return target;
    }

    @Override
    public void setMethod(Method method) {
        this.method = method;
    }

    @Override
    public void setParams(Object[] objs) {
        this.params = objs;
    }

    @Override
    public void setTarget(Object target) {
        this.target = target;
    }

}
