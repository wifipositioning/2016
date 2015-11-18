package com.wifipositioning.service.channel;

import java.util.HashMap;
import java.util.Map;

import io.netty.channel.socket.SocketChannel;

/**
 * Channel容器
 * <br/>
 * 保存服务端与客户端建立的连接
 * 
 * @author liuyujie
 * 
 *
 */
public class ChannelWrapper {
	public static Map<String, SocketChannel> channelWrapper = new HashMap<String, SocketChannel>();
	
	/**
	 * 删除无效Channel
	 * 
	 * @param channel
	 */
	public static void removeChannel(SocketChannel channel){
		for(Map.Entry<String, SocketChannel> entry : channelWrapper.entrySet()){
			if(entry.getValue() == channel){
				channelWrapper.remove(entry.getKey());
			}
		}
	}
	
	public static void addChannel(String clientId, SocketChannel channel){
		channelWrapper.put(clientId, channel);
	}
	
	/**
	 * 取得Channel,不存在返回<code>null</code>
	 * 
	 * @param clientId
	 * @return
	 */
	public static SocketChannel getChannel(String clientId){
		return channelWrapper.get(clientId);
	}
}
