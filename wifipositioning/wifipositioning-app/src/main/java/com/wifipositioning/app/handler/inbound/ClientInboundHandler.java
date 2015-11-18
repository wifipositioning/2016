package com.wifipositioning.app.handler.inbound;

import com.wifipositioning.model.msg.impl.ConnectMsg;
import com.wifipositioning.utils.uuid.UUIDGenerator;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

public class ClientInboundHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		SocketChannel channel = (SocketChannel) ctx.channel();
		String clientId = UUIDGenerator.generateUUID();
		ConnectMsg connectMsg = new ConnectMsg(clientId);
		System.out.println("====in channelActive====" + clientId);
		channel.writeAndFlush(connectMsg);
		super.channelActive(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("in channelRead");
		super.channelRead(ctx, msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("in channelReadComplete");
		super.channelReadComplete(ctx);
	}
}
