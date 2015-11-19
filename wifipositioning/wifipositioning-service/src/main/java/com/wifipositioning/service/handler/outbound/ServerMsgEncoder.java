package com.wifipositioning.service.handler.outbound;

import com.wifipositioning.model.msg.resp.AskBaseMsg;
import com.wifipositioning.model.msg.resp.impl.WifiPositioningAskMsg;
import com.wifipositioning.model.msg.type.MsgType;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 服务端消息编码器
 * <br/>
 * 根据消息类型进行相应的编码处理，由服务端发送给客户端
 * 
 * @author liuyujie
 *
 */
public class ServerMsgEncoder extends MessageToByteEncoder<AskBaseMsg> {

	@Override
	protected void encode(ChannelHandlerContext ctx, AskBaseMsg msg, ByteBuf out) throws Exception {
		
		byte msgType = msg.getMsgType();
		boolean isSuccess = msg.isSuccess();
		
		out.writeByte(msgType);
		out.writeBoolean(isSuccess);
		
		int messageLength = msg.getMessageLength();
		out.writeInt(messageLength);
		
		String message = msg.getMessage();
		// 有消息体		
		if(messageLength !=0 && message != null){
			out.writeBytes(message.getBytes());
		}
		
		if(msgType == MsgType.POSITIONING_ASK){
			System.out.println("==== 服务端 发送定位响应到 客户端 ====");
			WifiPositioningAskMsg wpAskMsg = (WifiPositioningAskMsg)msg; 
			out.writeFloat(wpAskMsg.getxPos());
			out.writeFloat(wpAskMsg.getyPos());
		}
		else if(msgType == MsgType.CREATE_DB_ASK){
			System.out.println("==== 服务端 发送建库响应到 客户端 ====");
		}
		else if(msgType == MsgType.CONNECT_ASK){
			System.out.println("==== 服务端 发送连接响应到 客户端 ====");
		}
		out.writeByte(msg.getEndFlag());
	}

}
