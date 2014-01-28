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
 * 使用tcp协议实现的对等端
 * 
 * @author fengjing.yfj
 * @version $Id: PeerImpl.java, v 0.1 2014年1月27日 下午3:16:51 fengjing.yfj Exp $
 */
public class TCPPeerImpl implements Peer {
    /** 通信的套接字 */
    private Socket             socket = null;

    /** 对象序列化流 */
    private ObjectInputStream  objectInputStream;

    /** 对象序列化输出流 */
    private ObjectOutputStream objectOutputStream;

    /** 
     * @see org.footoo.FLRMI.Peer#writeMessageMeta(org.footoo.FLRMI.MessageMeta)
     */
    @Override
    public void writeMessageMeta(MessageMeta messageMeta) {
        //初始化输出流
        initObjectOutputStream();

        try {
            objectOutputStream.writeObject(messageMeta);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FLRMIException("输出信元发生异常");
        }
    }

    /** 
     * @see org.footoo.FLRMI.Peer#readMessageMeta()
     */
    @Override
    public MessageMeta readMessageMeta() {
        //初始化输入流
        initObjectInputStream();
        try {
            return (MessageMeta) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new FLRMIException("读取信元发生异常");
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
            throw new FLRMIException("设置超时时间失败, 超时时间[" + timeout + "]");
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
            //关闭所有的流
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
            //忽略异常
        }

    }

    /**
     * 初始化outputStream
     */
    private void initObjectOutputStream() {
        if (socket == null && objectOutputStream == null) {
            throw new FLRMIException("无法获取object的输出流");
        }
        if (objectOutputStream == null) {
            try {
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                throw new FLRMIException("创建object的输出流失败");
            }
        }
    }

    /**
     * 初始化inputStream
     */
    private void initObjectInputStream() {
        if (socket == null && objectInputStream == null) {
            throw new FLRMIException("无法获取object的输入流");
        }
        if (objectInputStream == null) {
            try {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
                throw new FLRMIException("创建object的输入流失败");
            }
        }
    }

}
