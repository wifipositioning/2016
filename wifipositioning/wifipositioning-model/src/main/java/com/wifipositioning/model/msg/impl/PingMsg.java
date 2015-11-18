package com.wifipositioning.model.msg.impl;

import com.wifipositioning.model.msg.BaseMsg;
import com.wifipositioning.model.msg.type.MsgType;

/**
 * 心跳消息
 * 
 * @author liuyujie
 *
 */
public class PingMsg extends BaseMsg {

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
