package com.wifipositioning.service.handler.inbound;

import com.wifipositioning.model.msg.BaseMsg;
import com.wifipositioning.model.msg.impl.CreateDbMsg;
import com.wifipositioning.model.msg.impl.WifiPositioningMsg;
import com.wifipositioning.model.msg.type.MsgType;
import com.wifipositioning.service.channel.ChannelWrapper;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

public class ServerInboundHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("===Channel Read====");
		BaseMsg baseMsg = (BaseMsg) msg;
		String clientId = baseMsg.getClientId();
		byte msgType = baseMsg.getMsgType();
		
		SocketChannel channel = ChannelWrapper.getChannel(clientId);
		// 首次建立连接或连接已经失效		
		if(channel == null){
			// 客户端请求建立连接		
			if(msgType == MsgType.CONNECT){
				channel = (SocketChannel) ctx.channel();
				ChannelWrapper.addChannel(clientId, channel);
				System.out.println("===" + channel);
			}
			else{
				System.out.println("Please Establish Connection First");
			}
		}
		// 连接已经建立		
		else{
			// 定位请求消息			
			if(msgType == MsgType.POSITIONING){
				WifiPositioningMsg wifiPositioningMsg = (WifiPositioningMsg) msg;
				// Manager层的信息处理，计算得出定位位置，返回给客户端,或者异步回调发送位置信息
				System.out.println(wifiPositioningMsg);
			}
			else if(msgType == MsgType.CREATE_DB){
				CreateDbMsg createDbMsg = (CreateDbMsg) msg;
				//	Manager层的信息处理，存入数据库
				System.out.println(createDbMsg);
			}
			else if(msgType == MsgType.PING){
				System.out.println("Ping from client");
			}
			else if(msgType == MsgType.CONNECT){
				System.out.println("Ignore Connect from client");
			}
			else if(msgType == MsgType.DISCONNECT){
				System.out.println("Disconnect from client");
			}
		}
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("ChannelInactive");
		SocketChannel channel = (SocketChannel) ctx.channel();
		ChannelWrapper.removeChannel(channel);
		super.channelInactive(ctx);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println(cause.getMessage());
//		super.exceptionCaught(ctx, cause);
	}

}
