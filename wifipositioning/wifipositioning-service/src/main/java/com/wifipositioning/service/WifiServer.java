package com.wifipositioning.service;

import com.wifipositioning.service.handler.PosMsgEncoderHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Wifi室内定位服务器
 *
 */
public class WifiServer 
{
	private int port = 8080;
	
	private final int bossThreads = 0;
	private final int workerThreads = 0;
	
	private EventLoopGroup bossGroup = new NioEventLoopGroup(bossThreads);
    private EventLoopGroup workerGroup = new NioEventLoopGroup(workerThreads); 
    
    // 服务端channel连接    
    private SocketChannel serverChannel;
    
    public WifiServer() {
    	
	}
    
    public WifiServer(int port) {
    	this.port = port;
	}
    
    /**
     * 启动Wifi服务
     * 
     * @return 启动成功<code>true</code>, 启动失败或异常<code>false</code>
     */
    public boolean start(){
    	try{
    		run();
    	}
    	catch(Exception e){
    		return false;
    	}
    	return true;
    }
    
    private void run() throws InterruptedException{
    	ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup)
         .channel(NioServerSocketChannel.class)
         .childOption(ChannelOption.SO_KEEPALIVE, true)
         .childOption(ChannelOption.TCP_NODELAY, true)
         .childHandler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new PosMsgEncoderHandler());
				serverChannel = ch;
			}
		});
        
        try{
        	// Bind and start to accept incoming connections.
        	ChannelFuture f = b.bind(port).sync();
        	
        	// Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
        	f.channel().closeFuture().sync();
        }
        finally{
        	 workerGroup.shutdownGracefully();
             bossGroup.shutdownGracefully();
        }

    }
}
