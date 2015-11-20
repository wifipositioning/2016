package com.wifipositioning.model.msg.req.server;

import com.wifipositioning.model.msg.BaseMsg;
import com.wifipositioning.model.msg.type.MsgType;

/**
 * 服务端心跳检测PING消息
 * 
 * @author liuyujie
 *
 */
public class PingMsg extends BaseMsg {

	private static final long serialVersionUID = -6543258267365985392L;

	public PingMsg() {
		super(MsgType.PING);
	}

}
