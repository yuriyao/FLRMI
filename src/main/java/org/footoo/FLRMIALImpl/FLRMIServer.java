/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.FLRMIALImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.footoo.FLRMI.FLRMIException;

/**
 * ���Ƿ���ˣ���������̲߳���ȫ�ģ�Ӧ���ڵ��߳��е��û������ò�������
 * 
 * @author fengjing.yfj
 * @version $Id: FLRMIServer.java, v 0.1 2014��1��27�� ����6:57:46 fengjing.yfj Exp $
 */
public class FLRMIServer {

    /** ������׽��� */
    private final ServerSocket serverSocket;

    /** Ĭ�ϵĶ˿� */
    public static final int    DEFAULT_FL_RMI_PORT = 1223;

    /** Ĭ���̳߳� */
    private ExecutorService    threadPool          = null;

    /** ����״̬ */
    private volatile boolean   active              = false;

    /** �̳߳ص��߳����� */
    private int                threadNumber        = 20;

    public FLRMIServer() throws IOException {
        this(DEFAULT_FL_RMI_PORT);
    }

    public FLRMIServer(int port) throws IOException {
        this(port, 20);
    }

    public FLRMIServer(int port, int backLen) throws IOException {
        serverSocket = new ServerSocket(port, backLen);
    }

    /**
     * ʹ���̳߳�
     */
    public void useThreadPool() {
        if (active) {
            throw new FLRMIException("�����Ѿ��������޷������̳߳�");
        }
        threadPool = Executors.newFixedThreadPool(threadNumber);
    }

    /**
     * ��������
     */
    public void start() {
        active = true;
        while (active) {
            try {
                Socket socket = serverSocket.accept();
                //�����µ��̣߳����з���
                if (threadPool == null) {
                    new Thread(new ServerTask(socket)).start();
                }
                //ʹ���̳߳�
                else {
                    threadPool.execute(new ServerTask(socket));
                }
                //�رտͻ����߳�
                /* try {
                     socket.close();
                 } catch (IOException e) {
                     // �����쳣
                     e.printStackTrace();
                 }*/
            } catch (IOException e) {
                active = false;
                e.printStackTrace();
                throw new FLRMIException("�����������쳣");
            }
        }
    }

    /**
     * ֹͣ����
     */
    public void stop() {
        active = false;
        if (threadPool != null) {
            threadPool.shutdown();
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            //�����쳣
        }
    }
}
