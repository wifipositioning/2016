package com.wifipositioning.model.msg.req.client.impl;

import com.wifipositioning.model.msg.req.client.ReqBaseMsg;
import com.wifipositioning.model.msg.type.MsgType;

/**
 * 客户端连接建立消息
 * 
 * @author liuyujie
 *
 */
public class ConnectMsg extends ReqBaseMsg {

	private static final long serialVersionUID = -2289368235925892959L;

	/**
	 * 连接建立消息构造器
	 * 
	 * @param clientId 客户端Id
	 */
	public ConnectMsg(String clientId) {
		super(clientId, MsgType.CONNECT);
	}

}
