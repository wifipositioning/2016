package com.wifipositioning.service.handler.inbound;

import java.util.List;
import java.util.Map;

import com.wifipositioning.model.msg.impl.ConnectMsg;
import com.wifipositioning.model.msg.impl.CreateDbMsg;
import com.wifipositioning.model.msg.impl.DisconnectMsg;
import com.wifipositioning.model.msg.impl.PingMsg;
import com.wifipositioning.model.msg.impl.WifiPositioningMsg;
import com.wifipositioning.model.msg.state.MsgState;
import com.wifipositioning.model.msg.type.MsgType;
import com.wifipositioning.utils.msg.MacRssMappingSpilter;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

/**
 * 服务端消息解码器
 * <br/>
 * 根据消息类型进行相应的解码处理
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
		System.out.println("====in decode===");
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
				WifiPositioningMsg wifiPositioningMsg = new WifiPositioningMsg(clientId);
				wifiPositioningMsg.setRssis(macRssMapping);
				out.add(wifiPositioningMsg);
			}
			else if(msgType == MsgType.CREATE_DB){
				CreateDbMsg createDbMsg = new CreateDbMsg(clientId);
				createDbMsg.setRssis(macRssMapping);
				createDbMsg.setxPos(xPos);
				createDbMsg.setyPos(yPos);
				out.add(createDbMsg);
			}
			else if(msgType == MsgType.PING){
				PingMsg pingMsg = new PingMsg(clientId);
				out.add(pingMsg);
			}
			else if(msgType == MsgType.CONNECT){
				System.out.println("====Connect===" + clientId);
				ConnectMsg connectMsg = new ConnectMsg(clientId);
				out.add(connectMsg);
			}
			else if(msgType == MsgType.DISCONNECT){
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
