/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.FLRMIALImpl;

import java.io.IOException;
import java.lang.reflect.Proxy;

import org.footoo.FLRMIAL.FLRMITarget;

/**
 * ����ʵ������ʵ�ʵ��õı�̽ӿ�
 * 
 * @author fengjing.yfj
 * @version $Id: FLRMI.java, v 0.1 2014��1��28�� ����1:54:28 fengjing.yfj Exp $
 */
public abstract class FLRMI {

    /**
     * ��ȡFLRMIĬ��Э��ķ���
     * 
     * @param serviceName
     * @param host
     * @param port
     * @param interfaces
     * @return
     */
    public static Object getFLRMIService(String serviceName, String host, int port,
                                         Class<?> interfaces[]) {
        //����target
        FLRMITarget target = new FLRMITarget();
        target.setServiceName(serviceName);

        //���÷���
        return getService(target, host, port, interfaces);
    }

    /**
     * ����һ��ͨ�õķ���
     * 
     * @param target
     * @param host
     * @param port
     * @param interfaces
     * @return
     */
    public static Object getService(Object target, String host, int port, Class<?> interfaces[]) {
        //��������
        FLRMIProxy proxy = new FLRMIProxy();
        //����client
        FLRMIClient client = new FLRMIClient();
        client.setHost(host);
        client.setPort(port);

        //���ô���
        proxy.setClient(client);
        proxy.setTarget(target);
        return Proxy.newProxyInstance(FLRMIClient.class.getClassLoader(), interfaces, proxy);
    }

    /**
     * ע��FLRMI�ķ���
     * 
     * @param serviceName ������
     * @param service ����
     */
    public static void registerFLRMIService(String serviceName, Object service) {
        registerService(FLRMITarget.FL_RMI_PREFIX, serviceName, service);
    }

    /**
     * ͨ�õķ���ע��
     * 
     * @param prefix ����Э��ǰ׺
     * @param serviceName ������
     * @param service ����
     */
    public static void registerService(String prefix, Object serviceName, Object service) {
        ServiceCenterImpl.create().registerService(prefix, serviceName, service);
    }

    /**
     * ��ʼ����
     * 
     * @param server
     */
    public static void startServer(FLRMIServer server) {
        server.start();
    }

    /**
     * ʹ��Ĭ�϶˿ڿ�ʼ����
     * 
     * @throws IOException
     */
    public static void startServer() throws IOException {
        startServer(FLRMIServer.DEFAULT_FL_RMI_PORT);
    }

    /**
     * ʹ�ö˿�Ϊport�Ŀ�ʼ����
     * 
     * @param port �˿ں�
     * @throws IOException
     */
    public static void startServer(int port) throws IOException {
        FLRMIServer server = new FLRMIServer(port);
        startServer(server);
    }
}
