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
	 * 消息类型 
	 */
	private byte msgType;
	
	/**
	 * 消息结束标志位 <code>0</code>
	 */
	private byte endFlag = 0;
	
	public BaseMsg(byte msgType) {
		this.msgType = msgType;
	}
	
	public byte getMsgType() {
		return msgType;
	}
	public void setMsgType(byte msgType) {
		this.msgType = msgType;
	}

	/**
	 * 消息结束 <code>byte</code> 标识位
	 * 
	 * @return
	 */
	public byte getEndFlag() {
		return endFlag;
	}
	
}
