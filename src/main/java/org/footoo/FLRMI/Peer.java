/**
 * 
 */
package org.footoo.FLRMI;

/**
 * ����ͨѶ�ĶԵȶ�
 * 
 * @author fengjing.yfj
 *
 */
public interface Peer {
    /**
     * д��Ԫ
     * 
     * @param messageMeta ��Ԫ
     */
    void writeMessageMeta(MessageMeta messageMeta);

    /**
     * ��ȡһ����Ԫ
     * 
     * @return ������Ԫ
     */
    MessageMeta readMessageMeta();

    /**
     * ���ó�ʱʱ��
     * 
     * @param timeout ��ʱʱ�䣨ms��
     */
    void setTimeout(int timeout);

    /**
     * �رնԵȶ�
     */
    void shutdown();
}
