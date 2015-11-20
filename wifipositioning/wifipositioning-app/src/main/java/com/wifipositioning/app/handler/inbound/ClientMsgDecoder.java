package com.wifipositioning.app.handler.inbound;

import java.util.List;

import com.wifipositioning.app.ClientMsgState;
import com.wifipositioning.model.msg.resp.impl.ConnectAskMsg;
import com.wifipositioning.model.msg.resp.impl.CreateDbAskMsg;
import com.wifipositioning.model.msg.resp.impl.WifiPositioningAskMsg;
import com.wifipositioning.model.msg.type.MsgType;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

/**
 * 客户端消息解码器
 * <br/>
 * 根据消息类型进行相应的解码处理
 * 
 * @author liuyujie
 *
 */
public class ClientMsgDecoder extends ReplayingDecoder<ClientMsgState> {
	
	/**
	 * 消息类型
	 */
	private byte msgType;
	
	/**
	 * 服务端处理是否成功
	 */
	private boolean isSuccess;
	
	/**
	 * 参考点x坐标值
	 */
	private float xPos = 0;
	
	/**
	 * 参考点y坐标值
	 */
	private float yPos = 0;
	
	/**
	 * 消息总长度
	 */
	private int messageLength = 0;
	
	/**
	 * 消息字节数组
	 */
	private String message;
	
	public ClientMsgDecoder() {
		super(ClientMsgState.MSG_TYPE);
	}
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		switch (state()) {
		case MSG_TYPE:
			msgType = in.readByte();
			checkpoint(ClientMsgState.IS_SUCCESS);
		case IS_SUCCESS:
			isSuccess = in.readBoolean();
			checkpoint(ClientMsgState.MESSAGE_LENGTH);
		case MESSAGE_LENGTH:
			messageLength = in.readInt();
			checkpoint(ClientMsgState.MESSAGE);
		case MESSAGE:
			// 有消息体 		
			if(messageLength > 0){
				byte[] messageBytes = new byte[messageLength];
				in.readBytes(messageBytes, 0, messageLength);
				message = new String(messageBytes);
			}
			
			// 定位响应消息
			if(msgType == MsgType.POSITIONING_ASK){
				checkpoint(ClientMsgState.X_POS);
			}
			// 其他类型消息				
			else{
				checkpoint(ClientMsgState.END);
			}
			break;
		case X_POS:
			xPos = in.readFloat();
			checkpoint(ClientMsgState.Y_POS);
		case Y_POS:
			yPos = in.readFloat();
			checkpoint(ClientMsgState.END);
		case END:
			// 读走byte结束标志位			
			in.readByte();
			if(msgType == MsgType.POSITIONING_ASK){
				System.out.println("==== 客户端 解析 服务端 发送的定位响应码流 ====");
				WifiPositioningAskMsg wpAskMsg = messageLength <= 0 ? new WifiPositioningAskMsg(xPos, yPos, isSuccess) : new WifiPositioningAskMsg(xPos, yPos, isSuccess, message);
				out.add(wpAskMsg);
			}
			else if(msgType == MsgType.CREATE_DB_ASK){
				System.out.println("==== 客户端 解析 服务端 发送的建库响应码流 ====");
				CreateDbAskMsg createDbAskMsg = messageLength <= 0 ? new CreateDbAskMsg(isSuccess) : new CreateDbAskMsg(isSuccess, message);
				out.add(createDbAskMsg);
			}
			else if(msgType == MsgType.CONNECT_ASK){
				System.out.println("==== 客户端 解析 服务端 发送的连接响应码流 ====");
				ConnectAskMsg connectAskMsg = messageLength <= 0 ? new ConnectAskMsg(isSuccess) : new ConnectAskMsg(isSuccess, message);
				out.add(connectAskMsg);
			}
			// 状态还原			
			checkpoint(ClientMsgState.MSG_TYPE);
			break;
		default:
			throw new Exception("MsgState Exception");		
		}
		
	}

}
