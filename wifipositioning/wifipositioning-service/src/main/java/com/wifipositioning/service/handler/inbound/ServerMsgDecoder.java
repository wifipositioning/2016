package com.wifipositioning.service.handler.inbound;

import java.util.List;
import java.util.Map;

import com.wifipositioning.model.msg.req.impl.ConnectMsg;
import com.wifipositioning.model.msg.req.impl.CreateDbMsg;
import com.wifipositioning.model.msg.req.impl.DisconnectMsg;
import com.wifipositioning.model.msg.req.impl.PingMsg;
import com.wifipositioning.model.msg.req.impl.WifiPositioningMsg;
import com.wifipositioning.model.msg.state.MsgState;
import com.wifipositioning.model.msg.type.MsgType;
import com.wifipositioning.utils.msg.MacRssMappingSpilter;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

/**
 * 服务端消息解码器
 * <br/>
 * 根据消息类型进行相应的解码处理，解码客户端发送给服务端的消息
 * 
 * @author liuyujie
 *
 */
public class ServerMsgDecoder extends ReplayingDecoder<MsgState> {
	
	/**
	 * 客户端Id
	 */
	private String clientId;
	
	/**
	 * 消息类型
	 */
	private byte msgType;
	
	/**
	 * 参考点x坐标值
	 */
	private float xPos;
	
	/**
	 * 参考点y坐标值
	 */
	private float yPos;
	
	/**
	 * MAC RSS 映射总长度
	 */
	private int totalLength;
	
	/**
	 * MAC RSS 字节数组
	 */
	private byte[] macRsss;
	
	private Map<String, Integer> macRssMapping;
	
	public ServerMsgDecoder() {
		super(MsgState.MSG_TYPE);
	}
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		switch (state()) {
		case MSG_TYPE:
			msgType = in.readByte();
			checkpoint(MsgState.CLIENT_ID);
		case CLIENT_ID:
			// 36位UUID			
			byte[] clientIdBytes = new byte[36];
			in.readBytes(clientIdBytes, 0, 36);
			clientId = new String(clientIdBytes);
			if(msgType == MsgType.POSITIONING){
				checkpoint(MsgState.MAC_RSS_LENGTH);
			}
			else if(msgType == MsgType.CREATE_DB){
				checkpoint(MsgState.X_POS);
			}
			else{
				checkpoint(MsgState.END);
			}
			break;
		case X_POS:
			xPos = in.readFloat();
			checkpoint(MsgState.Y_POS);
		case Y_POS:
			yPos = in.readFloat();
			checkpoint(MsgState.MAC_RSS_LENGTH);
		case MAC_RSS_LENGTH:
			totalLength = in.readInt();
			macRsss = new byte[totalLength];
			checkpoint(MsgState.MAC_RSS_INFOS);
		case MAC_RSS_INFOS:
			in.readBytes(macRsss, 0, totalLength);
			String macRssStr = new String(macRsss);
			macRssMapping = MacRssMappingSpilter.spiltMacRsss(macRssStr);
			checkpoint(MsgState.END);
		case END:
			System.out.println(msgType);
			if(msgType == MsgType.POSITIONING){
				System.out.println("==== 服务端 解析 客户端 发送的定位请求码流 ====");
				WifiPositioningMsg wifiPositioningMsg = new WifiPositioningMsg(clientId);
				wifiPositioningMsg.setRssis(macRssMapping);
				out.add(wifiPositioningMsg);
			}
			else if(msgType == MsgType.CREATE_DB){
				System.out.println("==== 服务端 解析 客户端 发送的建库请求码流 ====");
				CreateDbMsg createDbMsg = new CreateDbMsg(clientId);
				createDbMsg.setRssis(macRssMapping);
				createDbMsg.setxPos(xPos);
				createDbMsg.setyPos(yPos);
				out.add(createDbMsg);
			}
			else if(msgType == MsgType.PING){
				System.out.println("==== 服务端 解析 客户端 发送的PING请求码流 ====");
				PingMsg pingMsg = new PingMsg(clientId);
				out.add(pingMsg);
			}
			else if(msgType == MsgType.CONNECT){
				System.out.println("==== 服务端 解析 客户端 发送的连接请求码流 ====");
				System.out.println("====Connect===" + clientId);
				ConnectMsg connectMsg = new ConnectMsg(clientId);
				out.add(connectMsg);
			}
			else if(msgType == MsgType.DISCONNECT){
				System.out.println("==== 服务端 解析 客户端 发送的连接释放请求码流 ====");
				DisconnectMsg disconnectMsg = new DisconnectMsg(clientId);
				out.add(disconnectMsg);
			}
			// 状态还原			
			checkpoint(MsgState.MSG_TYPE);
			break;
		default:
			throw new Exception("MsgState Exception");		
		}
		
	}

}
