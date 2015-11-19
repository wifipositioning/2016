package com.wifipositioning.app.handler.outbound;

import com.wifipositioning.app.utils.MacRssMappingString;
import com.wifipositioning.model.msg.req.ReqBaseMsg;
import com.wifipositioning.model.msg.req.impl.ConnectMsg;
import com.wifipositioning.model.msg.req.impl.CreateDbMsg;
import com.wifipositioning.model.msg.req.impl.DisconnectMsg;
import com.wifipositioning.model.msg.req.impl.PingMsg;
import com.wifipositioning.model.msg.req.impl.WifiPositioningMsg;
import com.wifipositioning.model.msg.type.MsgType;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ClientMsgEncoder extends MessageToByteEncoder<ReqBaseMsg> {
	
	@Override
	protected void encode(ChannelHandlerContext ctx, ReqBaseMsg msg, ByteBuf out) throws Exception {
		
		byte msgType = msg.getMsgType();
		String clientId = msg.getClientId();
		
		out.writeByte(msgType);
		out.writeBytes(clientId.getBytes());

		switch (msgType) {
		case MsgType.CONNECT:
			System.out.println("==== 客户端 发送连接请求到 服务端 ====");
			ConnectMsg connectMsg = (ConnectMsg) msg;
			break;
		case MsgType.CREATE_DB:
			System.out.println("==== 客户端 发送建库请求到 服务端 ====");
			CreateDbMsg createDbMsg = (CreateDbMsg) msg;
			out.writeFloat(createDbMsg.getxPos());
			out.writeFloat(createDbMsg.getyPos());
			String dbMacRssStr = MacRssMappingString.macRsssMappingString(createDbMsg.getRssis());
			out.writeInt(dbMacRssStr.length());
			out.writeBytes(dbMacRssStr.getBytes());
			break;
		case MsgType.POSITIONING:
			System.out.println("==== 客户端 发送定位请求到 服务端 ====");
			WifiPositioningMsg wifiPositioningMsg = (WifiPositioningMsg) msg;
			String positioningMacRssStr = MacRssMappingString.macRsssMappingString(wifiPositioningMsg.getRssis());
			out.writeInt(positioningMacRssStr.length());
			out.writeBytes(positioningMacRssStr.getBytes());
			break;
		case MsgType.PING:
			System.out.println("==== 客户端 发送PING请求到 服务端 ====");
			PingMsg pingMsg = (PingMsg) msg;
			break;
		case MsgType.DISCONNECT:
			System.out.println("==== 客户端 发送连接释放请求到 服务端 ====");
			DisconnectMsg disconnectMsg = (DisconnectMsg) msg;
			break;
		default:
			break;
		}
		
		out.writeByte(msg.getEndFlag());
	}

}
