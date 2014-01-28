/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.FLRMIALImpl;

import java.lang.reflect.Method;
import java.net.Socket;

import org.footoo.FLRMI.FLRMIException;
import org.footoo.FLRMI.MessageMeta;
import org.footoo.FLRMIImpl.MessageMetaImpl;
import org.footoo.FLRMIImpl.TCPPeerImpl;

/**
 * FLRMI �Ŀͻ���
 * 
 * @author fengjing.yfj
 * @version $Id: FLRMIClient.java, v 0.1 2014��1��28�� ����11:43:44 fengjing.yfj Exp $
 */
public class FLRMIClient {

    /** ����˵����� */
    private String host = "localhost";

    /** ����˵Ķ˿� */
    private int    port = FLRMIServer.DEFAULT_FL_RMI_PORT;

    /**
     * ����Զ�̷���
     * 
     * @param target
     * @param method
     * @param params
     * @param timeout ��ʱʱ��
     * @return
     */
    public Object invoke(Object target, Method method, Object params[], int timeout) {
        try {
            //�����ͻ����׽���
            Socket socket = new Socket(host, port);
            //�����Եȶ�
            TCPPeerImpl peer = new TCPPeerImpl();
            peer.setSocket(socket);
            //���ó�ʱʱ��
            if (timeout > 0) {
                peer.setTimeout(timeout);
            }
            //����������Ԫ
            MessageMetaImpl messageMeta = new MessageMetaImpl();
            messageMeta.setMethod(method);
            messageMeta.setTarget(target);
            messageMeta.setParams(params);
            //��������
            peer.writeMessageMeta(messageMeta);
            //�����Ӧ
            MessageMeta result = peer.readMessageMeta();
            //�����쳣
            if (result.getTarget() instanceof FLRMIException) {
                throw (FLRMIException) result.getTarget();
            }
            //�رնԵȶ�
            peer.shutdown();
            //������Ӧ
            return result.getTarget();
        } catch (FLRMIException e) {
            throw e;
        } catch (Exception e) {
            throw new FLRMIException(e);
        }

    }

    /**
     * Getter method for property <tt>host</tt>.
     * 
     * @return property value of host
     */
    public String getHost() {
        return host;
    }

    /**
     * Setter method for property <tt>host</tt>.
     * 
     * @param host value to be assigned to property host
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Getter method for property <tt>port</tt>.
     * 
     * @return property value of port
     */
    public int getPort() {
        return port;
    }

    /**
     * Setter method for property <tt>port</tt>.
     * 
     * @param port value to be assigned to property port
     */
    public void setPort(int port) {
        this.port = port;
    }

}
