package cn.ymsys.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.util.CommandArgumentParser;

import cm.ymsys.serivce.HomeService;
import cn.ymsys.bean.Home;

/**
 * @category 任务处理
 * @author yemnao
 *
 */
public class Trans implements Runnable {
	private ScheduledExecutorService service;
	private long initialDelay = 1;// 开始时间
	private long period = 1;// 间隔时间
	static HomeService hs = new HomeService();
	static int flag = 0;

	public Trans() {

	}

	public void start() {
		service = Executors.newScheduledThreadPool(10);
		service.scheduleAtFixedRate(new Trans(), initialDelay, period, TimeUnit.SECONDS);
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
		String[] args = { "" };
		test(args);
	}

	public void test(String[] args) {
		final GpioController gpio = GpioFactory.getInstance();
		Pin pin4 = CommandArgumentParser.getPin(RaspiPin.class, RaspiPin.GPIO_04, args);
		final GpioPinDigitalOutput output4 = gpio.provisionDigitalOutputPin(pin4, "My Output", PinState.LOW);
		output4.setShutdownOptions(false, PinState.LOW);
		try {
			Home home = hs.findById(1);
			if (home.getStatus() == 0) {
				if (flag == 0) {
					output4.setState(PinState.LOW);
					System.out.println("low");
					flag = 1;
				}
			} else {
				if (flag == 1) {
					output4.setState(PinState.HIGH);
					System.out.println("high");
					flag = 0;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			gpio.shutdown();
		}

	}

}
