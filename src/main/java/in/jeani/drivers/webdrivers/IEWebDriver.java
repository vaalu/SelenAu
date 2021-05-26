/**
 * 
 */
package in.jeani.drivers.webdrivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.springframework.stereotype.Component;

import in.jeani.drivers.IDriver;

/**
 * @author mohanavelp
 *
 */
@Component
public class IEWebDriver implements IDriver {

	private static WebDriver driver = null;
	
	@Override
	public WebDriver getDriver() {
		if (null == driver) {
			this.init();
		}
		return driver;
	}
	
	private void init() {
		System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
		driver = new InternetExplorerDriver();
	}
}
