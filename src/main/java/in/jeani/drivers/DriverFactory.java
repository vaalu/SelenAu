/**
 * 
 */
package in.jeani.drivers;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.jeani.drivers.webdrivers.ChromeWebDriver;
import in.jeani.drivers.webdrivers.FirefoxWebDriver;
import in.jeani.drivers.webdrivers.IEWebDriver;
import in.jeani.utils.PropertiesUtil;

/**
 * @author mohanavelp
 *
 */
@Component
public class DriverFactory {

	@Autowired
	PropertiesUtil props;

	@Autowired
	ChromeWebDriver chrome;

	@Autowired
	FirefoxWebDriver firefox;

	@Autowired
	IEWebDriver ie;

	public WebDriver getDriver() {
		String driverType = props.getDriverType();
		switch (driverType) {
		case "chrome":
			return chrome.getDriver();
		case "firefox":
			return firefox.getDriver();
		case "ie":
			return ie.getDriver();
		default:
			return chrome.getDriver();
		}
	}

}
