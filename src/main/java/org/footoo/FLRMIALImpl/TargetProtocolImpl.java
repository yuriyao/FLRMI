/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.FLRMIALImpl;

import org.footoo.FLRMIAL.TargetProtocol;

/**
 * Ŀ��Э�������ʵ��
 * 
 * @author fengjing.yfj
 * @version $Id: TargetProtocolImpl.java, v 0.1 2014��1��27�� ����7:31:18 fengjing.yfj Exp $
 */
public class TargetProtocolImpl implements TargetProtocol {

    /** Ŀ���ַ��� */
    private String target;

    /** Э��ǰ׺ */
    private String prefix;

    /** ������ */
    private String host;

    /** �˿ں� */
    private int    port;

    /** ·�� */
    private String path;

    public TargetProtocolImpl(String target) {
        this.target = target;

        //����Э�����
        resolve();
    }

    /**
     * Э�����������Э��ֽ�
     */
    private void resolve() {
        if (target.startsWith("/")) {

        }
    }

    /** 
      * @see org.footoo.FLRMIAL.TargetProtocol#getPrefix()
      */
    @Override
    public String getPrefix() {
        return prefix;
    }

    /** 
     * @see org.footoo.FLRMIAL.TargetProtocol#getHost()
     */
    @Override
    public String getHost() {
        return host;
    }

    /** 
     * @see org.footoo.FLRMIAL.TargetProtocol#getPort()
     */
    @Override
    public int getPort() {
        return port;
    }

    /** 
     * @see org.footoo.FLRMIAL.TargetProtocol#getPath()
     */
    @Override
    public String getPath() {
        return path;
    }

}
