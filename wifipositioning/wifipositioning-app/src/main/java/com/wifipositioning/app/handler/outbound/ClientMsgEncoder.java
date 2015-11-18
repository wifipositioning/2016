package com.wifipositioning.app.handler.outbound;

import com.wifipositioning.app.utils.MacRssMappingString;
import com.wifipositioning.model.msg.BaseMsg;
import com.wifipositioning.model.msg.impl.ConnectMsg;
import com.wifipositioning.model.msg.impl.CreateDbMsg;
import com.wifipositioning.model.msg.impl.DisconnectMsg;
import com.wifipositioning.model.msg.impl.PingMsg;
import com.wifipositioning.model.msg.impl.WifiPositioningMsg;
import com.wifipositioning.model.msg.type.MsgType;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.MessageToByteEncoder;

public class ClientMsgEncoder extends MessageToByteEncoder<BaseMsg> {
	
	@Override
	protected void encode(ChannelHandlerContext ctx, BaseMsg msg, ByteBuf out) throws Exception {
//		out.writeInt(1024);
		byte msgType = msg.getMsgType();
		ConnectMsg connectMsg = (ConnectMsg) msg;
		out.writeByte(connectMsg.getMsgType());
		out.writeBytes(connectMsg.getClientId().getBytes());
		out.writeByte(connectMsg.getEndFlag());
		System.out.println(msgType);
		System.out.println(connectMsg.getClientId());
//		ctx.w
//		ctx.channel().flush();
//		switch (msgType) {
//		case MsgType.CONNECT:
//			ConnectMsg connectMsg = (ConnectMsg) msg;
//			out.writeByte(connectMsg.getMsgType());
//			out.writeBytes(connectMsg.getClientId().getBytes());
//			out.writeByte(connectMsg.getEndFlag());
////			ctx.writeAndFlush(out);
//			break;
//		case MsgType.CREATE_DB:
//			CreateDbMsg createDbMsg = (CreateDbMsg) msg;
//			out.writeByte(createDbMsg.getMsgType());
//			out.writeBytes(createDbMsg.getClientId().getBytes());
//			out.writeFloat(createDbMsg.getxPos());
//			out.writeFloat(createDbMsg.getyPos());
//			String dbMacRssStr = MacRssMappingString.macRsssMappingString(createDbMsg.getRssis());
//			out.writeInt(dbMacRssStr.length());
//			out.writeBytes(dbMacRssStr.getBytes());
//			out.writeByte(createDbMsg.getEndFlag());
//			break;
//		case MsgType.POSITIONING:
//			WifiPositioningMsg wifiPositioningMsg = (WifiPositioningMsg) msg;
//			out.writeByte(wifiPositioningMsg.getMsgType());
//			out.writeBytes(wifiPositioningMsg.getClientId().getBytes());
//			String positioningMacRssStr = MacRssMappingString.macRsssMappingString(wifiPositioningMsg.getRssis());
//			out.writeInt(positioningMacRssStr.length());
//			out.writeBytes(positioningMacRssStr.getBytes());
//			out.writeByte(wifiPositioningMsg.getEndFlag());
//			break;
//		case MsgType.PING:
//			PingMsg pingMsg = (PingMsg) msg;
//			break;
//		case MsgType.DISCONNECT:
//			DisconnectMsg disconnectMsg = (DisconnectMsg) msg;
//			break;
//		default:
//			break;
//		}
	}

}
