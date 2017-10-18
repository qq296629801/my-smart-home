package cn.ymsys.main;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.util.CommandArgumentParser;

import cm.ymsys.serivce.HomeService;
import cn.ymsys.bean.Home;

public class Enter {
	static int flag = 0;
	static HomeService hs = new HomeService();

	public static void main(String[] args) {
		final GpioController gpio = GpioFactory.getInstance();
		Pin pin4 = CommandArgumentParser.getPin(RaspiPin.class, RaspiPin.GPIO_04, args);
		final GpioPinDigitalOutput output4 = gpio.provisionDigitalOutputPin(pin4, "My Output", PinState.LOW);
		output4.setShutdownOptions(false, PinState.LOW);
		while (true) {
			try {
				Thread.sleep(500);
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
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
