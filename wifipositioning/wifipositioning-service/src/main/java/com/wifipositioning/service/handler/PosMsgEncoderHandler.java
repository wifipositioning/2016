package com.wifipositioning.service.handler;

import com.wifipositioning.model.msg.OutboundPositioingInfo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 
 * 位置信息编码器
 * <br/>
 * 对定位位置{@link OutboundPositioingInfo}的坐标(xPos, yPos)进行编码,发送给客户端
 * 
 * @author liuyujie
 *
 */
public class PosMsgEncoderHandler extends MessageToByteEncoder<OutboundPositioingInfo> {

	@Override
	protected void encode(ChannelHandlerContext ctx, OutboundPositioingInfo msg, ByteBuf out) throws Exception {
		
		System.out.println("---enocde begin---");
		
		float xPos = msg.getxPos();
		float yPos = msg.getyPos();
		
		out.writeFloat(xPos);
		out.writeFloat(yPos);
		
		System.out.println("---enocde end---");
		
	}

}
