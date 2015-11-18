package com.wifipositioning.model.msg.impl;

import java.util.HashMap;
import java.util.Map;

import com.wifipositioning.model.msg.BaseMsg;
import com.wifipositioning.model.msg.type.MsgType;

/**
 * wifi定位请求信息
 * 
 * @author liuyujie
 *
 */
public class WifiPositioningMsg extends BaseMsg {

	private static final long serialVersionUID = -5796039796385687221L;
	
	private Map<String, Integer> rssis = new HashMap<String, Integer>();
	
	/**
	 * Wifi定位请求信息构造函数
	 * 
	 * @param clientId 客户端Id
	 */
	public WifiPositioningMsg(String clientId) {
		super(clientId, MsgType.POSITIONING);
	}
	
	public Map<String, Integer> getRssis() {
		return rssis;
	}

	public void setRssis(Map<String, Integer> rssis) {
		this.rssis = rssis;
	}
	
}
