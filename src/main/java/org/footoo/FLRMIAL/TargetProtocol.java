/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.FLRMIAL;

/**
 * Э�����
 * Э���ʽ prefix://host:port/path
 * 
 * @author fengjing.yfj
 * @version $Id: TargetProtocol.java, v 0.1 2014��1��27�� ����7:26:30 fengjing.yfj Exp $
 */
public interface TargetProtocol {

    /**
     * ��ȡЭ���ǰ׺
     * 
     * @return
     */
    public String getPrefix();

    /**
     * ��ȡĿ������
     * 
     * @return
     */
    public String getHost();

    /**
     * ��ȡЭ��˿�
     * 
     * @return
     */
    public int getPort();

    /**
     * ��ȡ·��
     * 
     * @return
     */
    public String getPath();
}
