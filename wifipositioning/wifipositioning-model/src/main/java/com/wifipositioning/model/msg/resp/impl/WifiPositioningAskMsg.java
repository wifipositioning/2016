package com.wifipositioning.model.msg.resp.impl;

import com.wifipositioning.model.msg.resp.AskBaseMsg;
import com.wifipositioning.model.msg.type.MsgType;

/**
 * 服务端定位响应消息
 * 
 * @author liuyujie
 *
 */
public class WifiPositioningAskMsg extends AskBaseMsg {

	private static final long serialVersionUID = -6294524815712593770L;
	
	private float xPos;
	
	private float yPos;
	
	/**
	 * 定位响应消息构造方法，无消息内容
	 * <br/>
	 * 一般构造失败消息使用
	 * 
	 * @param isSuccess 是否成功 成功<code>true</code> 失败<code>false</code>
	 */
	public WifiPositioningAskMsg(boolean isSuccess){
		this(0, 0, isSuccess);
	}
	
	/**
	 * 定位响应消息构造方法，带消息内容
	 * <br/>
	 * 一般构造失败消息使用
	 * 
	 * @param isSuccess 是否成功 成功<code>true</code> 失败<code>false</code>
	 * @param message 消息内容
	 */
	public WifiPositioningAskMsg(boolean isSuccess, String message){
		this(0, 0, isSuccess, message);
	}
	
	/**
	 * 定位响应消息构造方法，无消息内容
	 * <br/>
	 * 一般构造成功消息使用
	 * 
	 * @param xPos 定位位置x坐标
	 * @param yPos 定位位置y坐标
	 * @param isSuccess 是否成功 成功<code>true</code> 失败<code>false</code>
	 */
	public WifiPositioningAskMsg(float xPos, float yPos, boolean isSuccess){
		super(isSuccess, MsgType.POSITIONING_ASK);
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	/**
	 * 定位响应消息构造方法，带消息内容
	 * <br/>
	 * 一般构造成功消息使用
	 * 
	 * @param xPos 定位位置x坐标
	 * @param yPos 定位位置y坐标
	 * @param isSuccess 是否成功 成功<code>true</code> 失败<code>false</code>
	 */
	public WifiPositioningAskMsg(float xPos, float yPos, boolean isSuccess, String message){
		super(isSuccess, message, MsgType.POSITIONING_ASK);
		this.xPos = xPos;
		this.yPos = yPos;
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

}
