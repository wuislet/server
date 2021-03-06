package com.dyz.gameserver.bootstrap;

import com.dyz.gameserver.commons.message.MsgDispatcher;
import com.dyz.gameserver.context.ExecutorServiceManager;
import com.dyz.gameserver.net.MinaMsgHandler;
import com.dyz.gameserver.net.NetManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameServer {
	
	private static final Logger logger = LoggerFactory.getLogger(GameServer.class);

	private static int port = 10112;
	
	private static GameServer instance=new GameServer();
	
	public static MsgDispatcher msgDispatcher = new MsgDispatcher();;
	
	private static NetManager netManager;
	
	private GameServer(){
		netManager = new NetManager();
	}
	
	public static GameServer getInstance(){
		return instance;
	}
	
	public static void main(String[] args) {
		startUp();
	}
	public static void startUp(){
		try {
			logger.info("开始启动聊天服务器 ...");
			ExecutorServiceManager.getInstance().initExecutorService();
			logger.info("初始化聊天服务器线程池完成");
			netManager.startListner(new MinaMsgHandler(), port);//后台数据链接的时候再开一个listner
			logger.info("聊天服务器监听端口:{}完成",port);
			logger.info("game server started...");
		} catch (Exception e) {
			logger.error("聊天服务器启动失败");
			e.printStackTrace();
		}
		
	}
	
	public void stop() {
		try {
			//关闭服务器前，向所有线程玩家发送关闭消息
			logger.info("准备关闭聊天服务器...");
			netManager.stop();
			logger.info("聊天服务器停止网络监听");
			ExecutorServiceManager.getInstance().stop();
			logger.info("服务器线程池关闭完成");
			logger.info("服务器关闭完成");
		} catch (Exception e) {
			logger.info("服务器关闭异常");
			e.printStackTrace();
		}
	}
}
