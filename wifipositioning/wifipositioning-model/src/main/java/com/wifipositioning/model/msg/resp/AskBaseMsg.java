package com.wifipositioning.model.msg.resp;

import com.wifipositioning.model.msg.BaseMsg;

/**
 * 服务端响应抽象类
 * 
 * @author liuyujie
 *
 */
public abstract class AskBaseMsg extends BaseMsg {

	private static final long serialVersionUID = -260599719555118383L;

	private boolean isSuccess = false;
	
	private int messageLength = 0;
	
	private String message = null;
	
	/**
	 * 无消息内容构造方法
	 * 
	 * @param isSuccess
	 * @param msgType
	 */
	public AskBaseMsg(boolean isSuccess, byte msgType) {
		super(msgType);
		this.isSuccess = isSuccess;
	}
	
	/**
	 * 带消息内容构造方法
	 * 
	 * @param isSuccess
	 * @param message
	 * @param msgType
	 */
	public AskBaseMsg(boolean isSuccess, String message, byte msgType) {
		super(msgType);
		this.isSuccess = isSuccess;
		this.message = message;
		this.messageLength = message.length();
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public int getMessageLength() {
		return messageLength;
	}

//	public void setMessageLength(int messageLength) {
//		this.messageLength = messageLength;
//	}

	public String getMessage() {
		return message;
	}

//	public void setMessage(String message) {
//		this.message = message;
//	}
	
}
