package com.wifipositioning.service.handler;

import java.util.List;

import com.wifipositioning.model.msg.InboundPositioingInfo;
import com.wifipositioning.model.msg.state.PositioningState;
import com.wifipositioning.utils.msg.MacRssMappingSpilter;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

public class RssMsgDecoderHandler extends ReplayingDecoder<PositioningState> {

	/**
	 * MAC RSS 映射关系
	 */
	private InboundPositioingInfo macRssMappings = new InboundPositioingInfo();
	
	/**
	 * MAC RSS 映射总长度
	 */
	private int totalLength;
	
	/**
	 * MAC RSS 字节数组
	 */
	private byte[] macRsss;
	
	public RssMsgDecoderHandler() {
		super(PositioningState.START);
	}
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		switch (state()) {
		case TOTAL_LENGTH:
			totalLength = in.readInt();
			macRsss = new byte[totalLength];
			break;
		case MAC_RSS_INFOS:
			in.readBytes(macRsss, 0, totalLength);
			break;
		case END:
			String macRssStr = new String(macRsss);
			macRssMappings.setRssis(MacRssMappingSpilter.spiltMacRsss(macRssStr));
			out.add(macRssMappings);
			break;
		default:
			// Unknown State			
			break;
		}
	}

}
