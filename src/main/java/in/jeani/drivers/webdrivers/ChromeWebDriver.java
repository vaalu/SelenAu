/**
 * 
 */
package in.jeani.drivers.webdrivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.jeani.drivers.IDriver;
import in.jeani.utils.PropertiesUtil;

/**
 * @author mohanavelp
 *
 */
@Component
public class ChromeWebDriver implements IDriver {

	private static WebDriver driver = null;
	
	@Autowired
	PropertiesUtil props;
	
	@Override
	public WebDriver getDriver() {
		if (null == driver) {
			this.init();
		}
		return driver;
	}
	
	private void init() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		ChromeOptions options = this.getOptions();
		driver = new ChromeDriver(options);
	}
	
	private ChromeOptions getOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		return options;
	}

}
