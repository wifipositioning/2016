package com.wifipositioning.service;

import com.wifipositioning.service.handler.inbound.ServerIdleStateHandler;
import com.wifipositioning.service.handler.inbound.ServerInboundHandler;
import com.wifipositioning.service.handler.inbound.ServerMsgDecoder;
import com.wifipositioning.service.handler.outbound.ServerMsgEncoder;

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
				// 100s的读超时时间设置				
				ch.pipeline().addLast(new ServerIdleStateHandler(10, 0, 0));
				ch.pipeline().addLast(new ServerMsgDecoder());
				ch.pipeline().addLast(new ServerInboundHandler());
				ch.pipeline().addLast(new ServerMsgEncoder());
			}
		});
        
        try{
        	// Bind and start to accept incoming connections.
        	ChannelFuture future = b.bind(port).sync();
        	
        	if(future.isSuccess()){
        		System.out.println("Server Start Succeed!");
        	}
        	else{
        		System.out.println("Server Start Failed!");
        	}
        	
        	// Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
//        	future.channel().closeFuture().sync();
        }
        finally{
//        	 workerGroup.shutdownGracefully();
//             bossGroup.shutdownGracefully();
        }

    }
}
