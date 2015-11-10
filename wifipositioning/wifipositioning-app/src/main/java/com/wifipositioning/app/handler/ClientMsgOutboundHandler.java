package com.wifipositioning.app.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ClientMsgOutboundHandler extends MessageToByteEncoder<Integer> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Integer msg, ByteBuf out) throws Exception {
		System.out.println("Personal Encoder Method Msg is " + msg);
		int mint = msg;
		out.writeInt(mint);
	}

}
