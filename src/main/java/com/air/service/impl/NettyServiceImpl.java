package com.air.service.impl;

import com.air.netty.client.protocol.ModbusDecoder;
import com.air.netty.client.protocol.ModbusEncoder;
import com.air.netty.client.protocol.ModbusHandler;
import com.air.service.NettyService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;

/**
 * @Description
 * @Author semstouch
 * @Date 2016/12/12
 **/
@Component
public class NettyServiceImpl  implements NettyService{
    private static Logger logger = Logger.getLogger(NettyServiceImpl.class);
    @Autowired
    private ModbusHandler modbusHandler;


    private static final int MAX_FRAME_LENGTH = 270;
    private static final int LENGTH_FIELD_LENGTH = 1;
    private static final int LENGTH_FIELD_OFFSET =9;
    private static final int LENGTH_ADJUSTMENT = 2;
    private static final int INITIAL_BYTES_TO_STRIP = 0;


    @Override
    public void start(int port, ServletContext servletContext) {
        modbusHandler.setServletContext(servletContext);
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) // (3)
                    .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("decoder",
                                    new ModbusDecoder(MAX_FRAME_LENGTH, LENGTH_FIELD_OFFSET,LENGTH_FIELD_LENGTH,LENGTH_ADJUSTMENT, INITIAL_BYTES_TO_STRIP));
                            ch.pipeline().addLast("encoder", new ModbusEncoder());
                            ch.pipeline().addLast(modbusHandler);
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

            // 绑定端口，开始接收进来的连接
            ChannelFuture f = b.bind(port).sync(); // (7)

            logger.info("modbus 启动了:" + port);

            f.channel().closeFuture().sync();


        } catch (Exception e){
            logger.error("modbus error:"+e);
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            logger.info("modbus:关闭了");
        }

    }


}
