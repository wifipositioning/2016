package com.wifipositioning.model.msg.resp.impl;

import com.wifipositioning.model.msg.resp.AskBaseMsg;
import com.wifipositioning.model.msg.type.MsgType;

/**
 * 服务端建库响应消息
 * 
 * @author liuyujie
 *
 */
public class CreateDbAskMsg extends AskBaseMsg {

	private static final long serialVersionUID = 3843670156851075753L;

	/**
	 * 建库响应消息构造方法，无消息内容
	 * 
	 * @param isSuccess 是否成功 成功<code>true</code> 失败<code>false</code>
	 */
	public CreateDbAskMsg(boolean isSuccess) {
		super(isSuccess, MsgType.CREATE_DB_ASK);
	}

	
	/**
	 * 建库响应消息构造方法，带消息内容
	 * 
	 * @param isSuccess 是否成功 成功<code>true</code> 失败<code>false</code>
	 * @param message 消息内容
	 */
	public CreateDbAskMsg(boolean isSuccess, String message) {
		super(isSuccess, message, MsgType.CREATE_DB_ASK);
	}

}
