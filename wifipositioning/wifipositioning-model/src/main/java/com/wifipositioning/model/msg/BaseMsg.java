package com.wifipositioning.model.msg;

import java.io.Serializable;

/**
 * 消息抽象类
 * 
 * @author liuyujie
 *
 */
public abstract class BaseMsg implements Serializable{

	private static final long serialVersionUID = -8734170071771365851L;

	/**
	 * 客户端Id
	 */
	private String clientId;
	
	/**
	 * 消息类型 
	 */
	private byte msgType;
	
	/**
	 * 消息结束标志
	 */
	private byte endFlag = 0;
	
	public BaseMsg(String clientId, byte msgType) {
		this.clientId = clientId;
		this.msgType = msgType;
	}
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public byte getMsgType() {
		return msgType;
	}
	public void setMsgType(byte msgType) {
		this.msgType = msgType;
	}

	public byte getEndFlag() {
		return endFlag;
	}
	
}
