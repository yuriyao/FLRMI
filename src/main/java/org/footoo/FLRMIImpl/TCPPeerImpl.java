/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.FLRMIImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import org.footoo.FLRMI.FLRMIException;
import org.footoo.FLRMI.MessageMeta;
import org.footoo.FLRMI.Peer;

/**
 * ʹ��tcpЭ��ʵ�ֵĶԵȶ�
 * 
 * @author fengjing.yfj
 * @version $Id: PeerImpl.java, v 0.1 2014��1��27�� ����3:16:51 fengjing.yfj Exp $
 */
public class TCPPeerImpl implements Peer {
    /** ͨ�ŵ��׽��� */
    private Socket             socket = null;

    /** �������л��� */
    private ObjectInputStream  objectInputStream;

    /** �������л������ */
    private ObjectOutputStream objectOutputStream;

    /** 
     * @see org.footoo.FLRMI.Peer#writeMessageMeta(org.footoo.FLRMI.MessageMeta)
     */
    @Override
    public void writeMessageMeta(MessageMeta messageMeta) {
        //��ʼ�������
        initObjectOutputStream();

        try {
            objectOutputStream.writeObject(messageMeta);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FLRMIException("�����Ԫ�����쳣");
        }
    }

    /** 
     * @see org.footoo.FLRMI.Peer#readMessageMeta()
     */
    @Override
    public MessageMeta readMessageMeta() {
        //��ʼ��������
        initObjectInputStream();
        try {
            return (MessageMeta) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new FLRMIException("��ȡ��Ԫ�����쳣");
    }

    /** 
     * @see org.footoo.FLRMI.Peer#setTimeout(int)
     */
    @Override
    public void setTimeout(int timeout) {
        try {
            socket.setSoTimeout(timeout);
        } catch (SocketException e) {
            e.printStackTrace();
            throw new FLRMIException("���ó�ʱʱ��ʧ��, ��ʱʱ��[" + timeout + "]");
        }
    }

    /**
     * Getter method for property <tt>socket</tt>.
     * 
     * @return property value of socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Setter method for property <tt>socket</tt>.
     * 
     * @param socket value to be assigned to property socket
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void shutdown() {
        try {
            //�ر����е���
            if (objectInputStream != null) {
                objectInputStream.close();
                objectInputStream = null;
            }
            if (objectOutputStream != null) {
                objectOutputStream.close();
                objectOutputStream = null;
            }
            if (socket != null) {
                socket.close();
                socket = null;
            }
        } catch (Exception e) {
            //�����쳣
        }

    }

    /**
     * ��ʼ��outputStream
     */
    private void initObjectOutputStream() {
        if (socket == null && objectOutputStream == null) {
            throw new FLRMIException("�޷���ȡobject�������");
        }
        if (objectOutputStream == null) {
            try {
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                throw new FLRMIException("����object�������ʧ��");
            }
        }
    }

    /**
     * ��ʼ��inputStream
     */
    private void initObjectInputStream() {
        if (socket == null && objectInputStream == null) {
            throw new FLRMIException("�޷���ȡobject��������");
        }
        if (objectInputStream == null) {
            try {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
                throw new FLRMIException("����object��������ʧ��");
            }
        }
    }

}
