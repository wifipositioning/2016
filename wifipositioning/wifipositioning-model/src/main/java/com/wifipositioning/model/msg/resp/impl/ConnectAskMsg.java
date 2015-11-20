package com.wifipositioning.model.msg.resp.impl;

import com.wifipositioning.model.msg.resp.AskBaseMsg;
import com.wifipositioning.model.msg.type.MsgType;

/**
 * 服务端连接建立响应消息
 * 
 * @author liuyujie
 *
 */
public class ConnectAskMsg extends AskBaseMsg {

	private static final long serialVersionUID = 8157128894470953050L;

	/**
	 * 连接建立响应消息构造方法， 无消息内容
	 * 
	 * @param isSuccess 是否成功 成功<code>true</code> 失败<code>false</code>
	 */
	public ConnectAskMsg(boolean isSuccess) {
		super(isSuccess, MsgType.CONNECT_ASK);
	}
	
	/**
	 * 连接建立响应消息构造方法，带消息内容
	 * 
	 * @param isSuccess 是否成功 成功<code>true</code> 失败<code>false</code>
	 * @param message 消息内容
	 */
	public ConnectAskMsg(boolean isSuccess, String message) {
		super(isSuccess, message, MsgType.CONNECT_ASK);
	}

}
