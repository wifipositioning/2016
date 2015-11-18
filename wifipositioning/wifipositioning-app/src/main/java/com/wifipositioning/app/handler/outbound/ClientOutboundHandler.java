package com.wifipositioning.app.handler.outbound;

import java.net.SocketAddress;

import com.wifipositioning.model.msg.impl.ConnectMsg;
import com.wifipositioning.utils.uuid.UUIDGenerator;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.channel.socket.SocketChannel;

public class ClientOutboundHandler extends ChannelOutboundHandlerAdapter {

//	@Override
//	public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress,
//			ChannelPromise promise) throws Exception {
//		SocketChannel channel = (SocketChannel) ctx.channel();
//		String clientId = UUIDGenerator.generateUUID();
//		ConnectMsg connectMsg = new ConnectMsg(clientId);
//		System.out.println("====out connect====" + clientId);
//		channel.writeAndFlush(connectMsg);
//		super.connect(ctx, remoteAddress, localAddress, promise);
//	}

	@Override
	public void read(ChannelHandlerContext ctx) throws Exception {
		SocketChannel channel = (SocketChannel) ctx.channel();
		String clientId = UUIDGenerator.generateUUID();
		ConnectMsg connectMsg = new ConnectMsg(clientId);
		System.out.println("====out connect====" + clientId);
		channel.writeAndFlush(connectMsg);
		super.read(ctx);
	}

//	@Override
//	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
//		SocketChannel channel = (SocketChannel) ctx.channel();
//		String clientId = UUIDGenerator.generateUUID();
//		ConnectMsg connectMsg = new ConnectMsg(clientId);
//		System.out.println("====out connect====" + clientId);
//		channel.writeAndFlush(connectMsg);
//		super.write(ctx, msg, promise);
//	}
//
//	@Override
//	public void flush(ChannelHandlerContext ctx) throws Exception {
//		super.flush(ctx);
//	}

}
