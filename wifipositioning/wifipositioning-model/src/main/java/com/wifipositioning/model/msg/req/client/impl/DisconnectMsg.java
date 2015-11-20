package com.wifipositioning.model.msg.req.client.impl;

import com.wifipositioning.model.msg.req.client.ReqBaseMsg;
import com.wifipositioning.model.msg.type.MsgType;

/**
 * 客户端连接释放消息
 * 
 * @author liuyujie
 *
 */
public class DisconnectMsg extends ReqBaseMsg {

	private static final long serialVersionUID = 1505764347244326583L;

	/**
	 * 连接释放消息构造器
	 * 
	 * @param clientId 客户端Id
	 */
	public DisconnectMsg(String clientId) {
		super(clientId, MsgType.DISCONNECT);
	}

}
