package com.wifipositioning.model.msg.req.impl;

import java.util.HashMap;
import java.util.Map;

import com.wifipositioning.model.msg.req.ReqBaseMsg;
import com.wifipositioning.model.msg.type.MsgType;

/**
 * 位置指纹建库请求信息
 * 
 * @author liuyujie
 *
 */
public class CreateDbMsg extends ReqBaseMsg {

	private static final long serialVersionUID = -5471365801925996686L;
	
	private float xPos;
	
	private float yPos;
	
	private Map<String, Integer> rssis = new HashMap<String, Integer>();
	
	/**
	 * 数据库建立请求信息构造函数
	 * 
	 * @param clientId 客户端Id
	 */
	public CreateDbMsg(String clientId) {
		super(clientId, MsgType.CREATE_DB);
	}

	public float getxPos() {
		return xPos;
	}

	public void setxPos(float xPos) {
		this.xPos = xPos;
	}

	public float getyPos() {
		return yPos;
	}

	public void setyPos(float yPos) {
		this.yPos = yPos;
	}
	
	public Map<String, Integer> getRssis() {
		return rssis;
	}

	public void setRssis(Map<String, Integer> rssis) {
		this.rssis = rssis;
	}
	
}
