package com.wifipositioning.model.msg.req;

import com.wifipositioning.model.msg.BaseMsg;

/**
 * 客户端请求消息抽象类
 * 
 * @author liuyujie
 *
 */
public abstract class ReqBaseMsg extends BaseMsg {
	
	private static final long serialVersionUID = 9088841204312790327L;
	/**
	 * 客户端Id
	 */
	private String clientId;
	
	/**
	 * 请求基类构造方法
	 * @param msgType 消息类型
	 */
	public ReqBaseMsg(String clientId, byte msgType) {
		super(msgType);
		this.clientId = clientId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
