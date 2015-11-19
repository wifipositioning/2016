package com.wifipositioning.service.handler.inbound;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.wifipositioning.model.msg.req.ReqBaseMsg;
import com.wifipositioning.model.msg.req.impl.CreateDbMsg;
import com.wifipositioning.model.msg.req.impl.WifiPositioningMsg;
import com.wifipositioning.model.msg.resp.impl.ConnectAskMsg;
import com.wifipositioning.model.msg.resp.impl.CreateDbAskMsg;
import com.wifipositioning.model.msg.resp.impl.WifiPositioningAskMsg;
import com.wifipositioning.model.msg.type.MsgType;
import com.wifipositioning.service.channel.ChannelWrapper;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

/**
 * Server端的Inbound Handler
 * <br/>
 * 与Manager层的业务处理进行交互
 * 
 * @author liuyujie
 *
 */
public class ServerInboundHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("===in channelRead Method====");
		ReqBaseMsg reqBaseMsg = (ReqBaseMsg) msg;
		String clientId = reqBaseMsg.getClientId();
		byte msgType = reqBaseMsg.getMsgType();
		
		SocketChannel channel = ChannelWrapper.getChannel(clientId);
		// 首次建立连接或连接已经失效		
		if(channel == null){
			// 客户端请求建立连接		
			if(msgType == MsgType.CONNECT){
				System.out.println("==== 服务端 收到 客户端 发送的连接请求信息 ====");
				channel = (SocketChannel) ctx.channel();
				ChannelWrapper.addChannel(clientId, channel);
				// 连接成功消息返回给客户端				
				ConnectAskMsg connectAskMsg = new ConnectAskMsg(true);
				channel.writeAndFlush(connectAskMsg);
			}
			else{
				System.out.println("Please Establish Connection First");
				// 连接失败消息返回给客户端	
				ConnectAskMsg connectAskMsg = new ConnectAskMsg(false, "Please Establish Connection First");
				ctx.channel().writeAndFlush(connectAskMsg);
			}
		}
		// 连接已经建立		
		else{
			// 定位请求消息			
			if(msgType == MsgType.POSITIONING){
				System.out.println("==== 服务端 收到 客户端 发送的定位请求信息 ====");
				WifiPositioningMsg wifiPositioningMsg = (WifiPositioningMsg) msg;
				// Manager层的信息处理，计算得出定位位置，返回给客户端,或者异步回调发送位置信息
				Map<String, Integer> macRsss = wifiPositioningMsg.getRssis();
				Set<Entry<String, Integer>> entries = macRsss.entrySet();
				
				for(Entry<String, Integer> entry : entries){
					System.out.println(entry.getKey());
					System.out.println(entry.getValue());
				}
				System.out.println(wifiPositioningMsg.getRssis());
				WifiPositioningAskMsg wpAskMsg = new WifiPositioningAskMsg(10.0f, 12.5f, true);
				channel.writeAndFlush(wpAskMsg);
			}
			else if(msgType == MsgType.CREATE_DB){
				System.out.println("==== 服务端 收到 客户端 发送的建库请求信息 ====");
				CreateDbMsg createDbMsg = (CreateDbMsg) msg;
				//	Manager层的信息处理，存入数据库
				System.out.println(createDbMsg);
				CreateDbAskMsg createDbAskMsg = new CreateDbAskMsg(true);
				channel.writeAndFlush(createDbAskMsg);
			}
			else if(msgType == MsgType.PING){
				System.out.println("Ping from client");
			}
			else if(msgType == MsgType.CONNECT){
				// 多次连接不响应				
				System.out.println("Ignore Connect from client");
				// 连接成功消息返回给客户端				
//				ConnectAskMsg connectAskMsg = new ConnectAskMsg(true);
//				channel.writeAndFlush(connectAskMsg);
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
