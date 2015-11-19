package com.wifipositioning.app;

/**
 * 客户端状态记录器
 * 
 * @author liuyujie
 *
 */
public class ClientStateRecoder {
	
	private static ClientState clientState = ClientState.EMPTY;
	
	public static ClientState getClientState() {
		return clientState;
	}

	public synchronized static void setClientState(ClientState clientState) {
		ClientStateRecoder.clientState = clientState;
	}

	public enum ClientState {
		
		/**
		 * 空状态
		 */
		EMPTY,
		
		/**
		 * 连接成功
		 */
		S_CONNECT,
		
		/**
		 * 连接失败
		 */
		F_CONNECT,
		
		/**
		 * 单条建库消息推送成功
		 */
		S_CREATE_DB,
		
		/**
		 * 单条建库消息推送成功
		 */
		F_CREATE_DB,
		
		/**
		 * 本次定位成功
		 */
		S_POSITIONING,
		
		/**
		 * 本次定位失败
		 */
		F_POSITIONING,
		
	}
}
