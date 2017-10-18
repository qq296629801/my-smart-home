package cn.ymsys.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @category 转码任务处理
 * @author xiaomi
 *
 */
public class MyRunnable implements Runnable {
	private ScheduledExecutorService service;
	private long initialDelay = 1;// 开始时间
	private long period = 1;// 间隔时间

	public MyRunnable() {

	}

	public void start() { // start方法当web容器启动时调用，在这里启动监听线程。
		System.out.println("开始进程！");
		service = Executors.newScheduledThreadPool(10);
		// 从现在开始3秒钟之后，每隔1800秒钟执行一次
		service.scheduleAtFixedRate(new MyRunnable(), initialDelay, period, TimeUnit.SECONDS);
	}

	/***
	 * 马上停止`
	 */
	public void stop() {
		if (this.service != null) {
			this.service.shutdownNow();
		}
	}

	/***
	 * 执行完后面的任务再停止
	 */
	public void shutdown() {
		if (this.service != null) {
			this.service.shutdown();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (Const.flag == 1) {
			MailUtils cn = new MailUtils();
			// 设置发件人地址、收件人地址和邮件标题
			cn.setAddress("qq296629801@163.com", "qq296629801@163.com", "家里遭小偷了，请注意！");
			// 设置要发送附件的位置和标题
			cn.send("smtp.163.com", "qq296629801@163.com", "qq296629801");
			Const.flag = 0;
		}
	}

}
