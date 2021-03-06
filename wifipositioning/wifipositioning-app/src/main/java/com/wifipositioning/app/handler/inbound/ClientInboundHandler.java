package com.wifipositioning.app.handler.inbound;

import java.util.HashMap;
import java.util.Map;

import com.wifipositioning.app.ClientStateRecoder;
import com.wifipositioning.app.ClientStateRecoder.ClientState;
import com.wifipositioning.model.msg.BaseMsg;
import com.wifipositioning.model.msg.req.client.impl.ConnectMsg;
import com.wifipositioning.model.msg.req.client.impl.PingMsg;
import com.wifipositioning.model.msg.req.client.impl.WifiPositioningMsg;
import com.wifipositioning.model.msg.resp.AskBaseMsg;
import com.wifipositioning.model.msg.resp.impl.ConnectAskMsg;
import com.wifipositioning.model.msg.resp.impl.CreateDbAskMsg;
import com.wifipositioning.model.msg.resp.impl.WifiPositioningAskMsg;
import com.wifipositioning.model.msg.type.MsgType;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

public class ClientInboundHandler extends ChannelInboundHandlerAdapter {

	private String clientId = null;
	
	public ClientInboundHandler(String clientId) {
		this.clientId = clientId;
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// 首次建立连接		
		System.out.println("====in channelActive Method====" + clientId);
		SocketChannel channel = (SocketChannel) ctx.channel();
		ConnectMsg connectMsg = new ConnectMsg(clientId);
		channel.writeAndFlush(connectMsg);
		super.channelActive(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// 消息读取		
		System.out.println("in channelRead Method");
		BaseMsg baseMsg = (BaseMsg) msg;
		byte msgType = baseMsg.getMsgType();
		boolean isSuccess = true;
		// 不是PING消息，则是服务端响应反馈信息		
		if(msgType != MsgType.PING){
			AskBaseMsg askBaseMsg = (AskBaseMsg)msg;
			isSuccess = askBaseMsg.isSuccess();
		}
		
		switch (msgType) {
		case MsgType.POSITIONING_ASK:
			System.out.println("==== 客户端 收到 服务端 发送的定位响应信息 ====");
			WifiPositioningAskMsg wpAskMsg = (WifiPositioningAskMsg) msg;
			if(isSuccess){
				ClientStateRecoder.setClientState(ClientState.S_POSITIONING);
				System.out.println("定位成功");
			}
			else{
				ClientStateRecoder.setClientState(ClientState.F_POSITIONING);
				System.out.println(wpAskMsg.getMessage());
			}
			break;
		case MsgType.CREATE_DB_ASK:
			System.out.println("==== 客户端 收到 服务端 发送的建库响应信息 ====");
			CreateDbAskMsg createDbAskMsg = (CreateDbAskMsg) msg;
			if(isSuccess){
				ClientStateRecoder.setClientState(ClientState.S_CREATE_DB);
				System.out.println("建库成功");
				// 可以进行下一次建库信息发送				
			}
			else{
				ClientStateRecoder.setClientState(ClientState.F_CREATE_DB);
				System.out.println(createDbAskMsg.getMessage());
				// 需要重新发送				
			}
			break;
		case MsgType.PING:
			System.out.println("==== 客户端 收到 服务端 发送的心跳检测信息 ====");
			PingMsg pingMsg = new PingMsg(clientId);
			ctx.channel().writeAndFlush(pingMsg);
			break;
		case MsgType.CONNECT_ASK:
			System.out.println("==== 客户端 收到 服务端 发送的连接响应信息 ====");
			ConnectAskMsg connectAskMsg = (ConnectAskMsg) msg;
			if(isSuccess){
				ClientStateRecoder.setClientState(ClientState.S_CONNECT);
				System.out.println("连接成功");	
				System.out.println("====客户端 发送 定位请求到 服务端");	
				testPositioning(ctx);
				
//				testCreateDb(ctx);
			}
			// 失败重连			
			else{
				ClientStateRecoder.setClientState(ClientState.F_CONNECT);
				System.out.println("连接失败");
				System.out.println(connectAskMsg.getMessage());
				System.out.println("尝试重新连接");
				// 休眠3s在重新连接				
				Thread.sleep(3000);
				ConnectMsg connectMsg = new ConnectMsg(clientId);
				ctx.channel().writeAndFlush(connectMsg);
			}
			break;
		default:
			throw new Exception("Unknown Msg Type:" + msgType);
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("in channelReadComplete");
		super.channelReadComplete(ctx);
	}
	
	/**
	 * 测试定位
	 */
	private void testPositioning(ChannelHandlerContext ctx){
		WifiPositioningMsg wifiPositioningMsg = new WifiPositioningMsg(clientId);
		Map<String,Integer> macRsss = new HashMap<String, Integer>();
		macRsss.put("ad:45:23:e4:2f:7d", 65);
		wifiPositioningMsg.setRssis(macRsss);
		ctx.channel().writeAndFlush(wifiPositioningMsg);
	}
	
	/**
	 * 测试建库
	 */
	private void testCreateDb(ChannelHandlerContext ctx){
		
	}
}
