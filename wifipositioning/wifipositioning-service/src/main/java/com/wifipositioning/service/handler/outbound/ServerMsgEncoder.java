package com.wifipositioning.service.handler.outbound;

import com.wifipositioning.model.msg.BaseMsg;
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
public class ServerMsgEncoder extends MessageToByteEncoder<BaseMsg> {

	@Override
	protected void encode(ChannelHandlerContext ctx, BaseMsg msg, ByteBuf out) throws Exception {
		
		byte msgType = msg.getMsgType();
		
		out.writeByte(msgType);
		// 不是PING消息，则为响应消息		
		if(msgType != MsgType.PING){
			AskBaseMsg askMsg = (AskBaseMsg) msg;
			boolean isSuccess = askMsg.isSuccess();
			
			out.writeBoolean(isSuccess);
			
			int messageLength = askMsg.getMessageLength();
			out.writeInt(messageLength);
			
			String message = askMsg.getMessage();
			// 有消息体		
			if(messageLength !=0 && message != null){
				out.writeBytes(message.getBytes());
			}
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
		else if(msgType == MsgType.PING){
			System.out.println("==== 服务端 发送心跳检测到 客户端 ====");
		}
		else if(msgType == MsgType.CONNECT_ASK){
			System.out.println("==== 服务端 发送连接响应到 客户端 ====");
		}
		out.writeByte(msg.getEndFlag());
	}

}
