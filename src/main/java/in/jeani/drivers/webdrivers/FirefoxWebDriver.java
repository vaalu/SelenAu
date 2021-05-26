/**
 * 
 */
package in.jeani.drivers.webdrivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.stereotype.Component;

import in.jeani.drivers.IDriver;

/**
 * @author mohanavelp
 *
 */
@Component
public class FirefoxWebDriver implements IDriver {

private static WebDriver driver = null;
	
	@Override
	public WebDriver getDriver() {
		if (null == driver) {
			this.init();
		}
		return driver;
	}
	
	private void init() {
		System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
		driver = new FirefoxDriver();
	}

}
