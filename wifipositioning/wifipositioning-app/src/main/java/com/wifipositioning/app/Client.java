package com.wifipositioning.app;

import com.wifipositioning.app.handler.ClientMsgOutboundHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
	
	private int port = 8080;
	private String host = "127.0.0.1";
	
	private EventLoopGroup workerGroup = new NioEventLoopGroup();
	
	private SocketChannel clientChannel;
	
	public static void main(String[] args) {
		Client client = new Client();
		try {
			client.run();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void run() throws InterruptedException{
		try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ClientMsgOutboundHandler());
                	clientChannel = ch;
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync(); // (5)
            
            clientChannel.writeAndFlush(1024);
            // Wait until the connection is closed.
//            f.channel().closeFuture().sync();
        } finally {
//            workerGroup.shutdownGracefully();
        }
	}

}
