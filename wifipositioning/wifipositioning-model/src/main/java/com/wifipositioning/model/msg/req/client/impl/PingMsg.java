package com.wifipositioning.model.msg.req.client.impl;

import com.wifipositioning.model.msg.req.client.ReqBaseMsg;
import com.wifipositioning.model.msg.type.MsgType;

/**
 * 客户端心跳消息
 * 
 * @author liuyujie
 *
 */
public class PingMsg extends ReqBaseMsg {

	private static final long serialVersionUID = 8186860591595157979L;

	/**
	 * 心跳消息构造器
	 * 
	 * @param clientId 客户端Id
	 */
	public PingMsg(String clientId) {
		super(clientId, MsgType.PING);
	}

}
