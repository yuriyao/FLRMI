/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.FLRMIALImpl;

import java.net.Socket;

import org.footoo.FLRMI.FLRMIException;
import org.footoo.FLRMI.MessageMeta;
import org.footoo.FLRMIAL.ServiceFinder;
import org.footoo.FLRMIAL.TargetFinderCenter;
import org.footoo.FLRMIImpl.MessageMetaImpl;
import org.footoo.FLRMIImpl.TCPPeerImpl;

/**
 * ����˵Ĺ����߳�
 * 
 * @author fengjing.yfj
 * @version $Id: ServerTesk.java, v 0.1 2014��1��27�� ����7:16:23 fengjing.yfj Exp $
 */
public class ServerTask implements Runnable {

    /** �Ϳͻ��˵����� */
    private Socket socket;

    public ServerTask(Socket socket) {
        this.socket = socket;
    }

    /** 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {

        try {
            //��ŷ��ؽ��
            Object result = null;
            TCPPeerImpl peer = new TCPPeerImpl();
            try {

                peer.setSocket(socket);

                //��ȡ��Ԫ
                MessageMeta messageMeta = peer.readMessageMeta();

                //������Ԫ��ȡ����
                ServiceFinder finder = TargetFinderCenter.getFinder(messageMeta.getTarget());
                //�����������û��ע��
                if (finder == null) {
                    throw new FLRMIException("�ڷ����������TargetFindCenterû�л�ȡ��["
                                             + messageMeta.getTarget() + "]�ķ��������");
                }
                //��ȡ����
                Object service = finder.getService(messageMeta.getTarget());
                if (service == null) {
                    throw new FLRMIException("û�л�ȡ��Ŀ��[" + messageMeta.getTarget() + "]�ķ���");
                }
                //û�п��Ե��õķ��񷽷�
                if (messageMeta.getMethod() == null) {
                    throw new FLRMIException("");
                }

                //����һϵ�еĿ��������ڿ��Խ��з���ĵ�����
                result = messageMeta.getMethod().invoke(service, messageMeta.getParams());

            } catch (Exception e) {
                e.printStackTrace();
                result = new FLRMIException(e);
            }

            //���ͻ��˷��ؽ��
            MessageMetaImpl messageMetaImpl = new MessageMetaImpl();
            messageMetaImpl.setTarget(result);
            //������Ϣ
            peer.writeMessageMeta(messageMetaImpl);
            //�رնԵȵ�
            peer.shutdown();

        } catch (Exception e) {
            //�����쳣
            e.printStackTrace();
        }
    }
}
