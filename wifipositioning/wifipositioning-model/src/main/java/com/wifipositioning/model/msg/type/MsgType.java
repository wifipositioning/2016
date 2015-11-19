package com.wifipositioning.model.msg.type;

/**
 * 消息类型
 * 
 * @author liuyujie
 *
 */
public class MsgType {
	
	/**
	 * 建立连接
	 */
	public static final byte CONNECT = 0;
	
	/**
	 * 心跳检测
	 */
	public static final byte PING = 1;
	
	/**
	 * 建立数据库
	 */
	public static final byte CREATE_DB = 2;
	
	/**
	 * 定位请求
	 */
	public static final byte POSITIONING = 3;
	
	/**
	 * 释放连接
	 */
	public static final byte DISCONNECT = 4;
	
	/**
	 * 服务端连接响应
	 */
	public static final byte CONNECT_ASK = 5;
	
	/**
	 * 服务端建库响应
	 */
	public static final byte CREATE_DB_ASK = 6;
	
	/**
	 * 服务端定位响应
	 */
	public static final byte POSITIONING_ASK = 7;
}
