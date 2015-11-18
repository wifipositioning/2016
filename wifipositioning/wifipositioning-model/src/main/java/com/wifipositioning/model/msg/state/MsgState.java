package com.wifipositioning.model.msg.state;

/**
 * 消息状态标识
 * <br/>
 * 1.连接建立信息
 * <br/>
 * 2.建库信息：每采集一个参考点(x,y)，就发送一次数据
 * <br/>
 * 3.定位信息：每需要获取一次定位位置，就发送一次数据，相比于建库信息，缺少参考节点位置坐标(x,y)
 * <br/>
 * 4.连接释放信息
 * 
 * @author liuyujie
 *
 */
public enum MsgState {
	
	/**
	 * <code>byte</code>消息类型
	 */
	MSG_TYPE,
	
	/**
	 * <code>String</code> 客户端Id
	 */
	CLIENT_ID,
	
	/**
	 * <code>float</code> 参考节点x坐标
	 */
	X_POS,
	
	/**
	 * <code>float</code> 参考节点y坐标
	 */
	Y_POS,

	/**
	 * <code>int</code> 单次消息发送的mac-rss字节数组长度
	 */
	MAC_RSS_LENGTH,
	
	/**
	 * <code>byte[]</code> 单次消息发送的mac-rss字节数组
	 */
	MAC_RSS_INFOS,
	
	/**
	 * <code>byte</code> 消息结束标志位
	 */
	END,
}
