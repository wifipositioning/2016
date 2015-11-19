package com.wifipositioning.app;

/**
 * 消息状态标识
 * <br/>
 * 1.连接建立响应信息
 * <br/>
 * 2.建库响应信息：每发送一次建库数据，服务端返回一次建库结果
 * <br/>
 * 3.定位响应信息：每发送一次定位请求，服务端返回一次定位结果，相比于建库信息，多了定位结果位置坐标(x,y)
 * <br/>
 * 4.连接释放信息
 * 
 * @author liuyujie
 *
 */
public enum ClientMsgState {
	
	/**
	 * <code>byte</code>消息类型
	 */
	MSG_TYPE,
	
	/**
	 * <code>boolean</code> 服务端消息处理是否成功
	 */
	IS_SUCCESS,
	
	/**
	 * <code>int</code> 单次消息字节数组长度
	 */
	MESSAGE_LENGTH,
	
	/**
	 * <code>byte[]</code> 单次消息发送字节数组
	 */
	MESSAGE,

	/**
	 * <code>float</code> 定位结果x坐标
	 */
	X_POS,
	
	/**
	 * <code>float</code> 定位结果y坐标
	 */
	Y_POS,
	
	/**
	 * <code>byte</code> 消息结束标志位
	 */
	END,
}
