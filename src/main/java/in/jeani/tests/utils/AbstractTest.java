/**
 * 
 */
package in.jeani.tests.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.jeani.utils.PropertiesUtil;

/**
 * @author mohanavelp
 *
 */
@Component
public abstract class AbstractTest {

	@Autowired
	PropertiesUtil props;
	
	private WebDriver driver;
	private String screenShotsDir = "/var";
	
	public void init() {
		screenShotsDir = props.getScreenshotsDir();
		this.cleanUp();
	}
	
	private void cleanUp(String filename) {
		File f = new File(filename);
		if (f.exists() && f.isDirectory()) {
			for (File dirFile : f.listFiles()) {
				dirFile.delete();
			}
		}
	}
	
	public void cleanUp() {
		this.cleanUp(screenShotsDir);
	}
	
	private int getNextCount(String dirPath) {
		int ssCount = 0;
		File f = new File(dirPath);
		if(f.exists() && f.isDirectory()) {
			ssCount = f.listFiles().length;
		}
		return ssCount;
	}
	private String getNextScreenshotPath(String dirPath, String name) {
		int count = this.getNextCount(dirPath);
		String formatted = String.format("%04d", count);
		return dirPath + formatted + "_" + name + ".png";
	}
	public void takeScreenshot(String name) {
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			String path = this.getNextScreenshotPath(screenShotsDir, name);
			File destPath = new File(path);
			FileUtils.copyFile(src, destPath);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the driver
	 */
	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * @param driver the driver to set
	 */
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
}
