package com.wifipositioning.service.handler.inbound;

import io.netty.handler.timeout.IdleStateHandler;

/**
 * 服务端空闲检测 handler
 * <br/>
 * 用于心跳检测
 * 
 * @author liuyujie
 *
 */
public class ServerIdleStateHandler extends IdleStateHandler {

	/**
	 * ServerIdleStateHandler构造方法
	 * <br/>
	 * 确定超时时间
	 * 
	 * @param readerIdleTimeSeconds 读超时时间，数字0则忽略空闲事件
	 * @param writerIdleTimeSeconds 写超时时间，数字0则忽略空闲事件
	 * @param allIdleTimeSeconds 读写超时时间，数字0则忽略空闲事件
	 */
	public ServerIdleStateHandler(int readerIdleTimeSeconds, int writerIdleTimeSeconds, int allIdleTimeSeconds) {
		super(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds);
	}

}
