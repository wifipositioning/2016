package com.wifipositioning.model.msg.req.impl;

import com.wifipositioning.model.msg.req.ReqBaseMsg;
import com.wifipositioning.model.msg.type.MsgType;

/**
 * 连接建立消息
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
