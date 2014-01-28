/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.FLRMIALImpl;

import org.footoo.FLRMIAL.TargetProtocol;

/**
 * 目标协议解析的实现
 * 
 * @author fengjing.yfj
 * @version $Id: TargetProtocolImpl.java, v 0.1 2014年1月27日 下午7:31:18 fengjing.yfj Exp $
 */
public class TargetProtocolImpl implements TargetProtocol {

    /** 目标字符串 */
    private String target;

    /** 协议前缀 */
    private String prefix;

    /** 主机名 */
    private String host;

    /** 端口号 */
    private int    port;

    /** 路径 */
    private String path;

    public TargetProtocolImpl(String target) {
        this.target = target;

        //进行协议解析
        resolve();
    }

    /**
     * 协议解析，进行协议分解
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
