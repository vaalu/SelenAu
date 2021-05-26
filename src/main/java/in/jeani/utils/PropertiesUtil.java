/**
 * 
 */
package in.jeani.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author mohanavelp
 *
 */
@Component
public class PropertiesUtil {

	@Value("${selenium.driver.type}")
	private String driverType;

	@Value("${app.screenshots.dir}")
	private String screenshotsDir;
	
	@Value("${app.base.url}")
	private String baseUrl;
	
	@Value("${app.screen.timeout.seconds}")
	private String timeoutValue;

	@Value("${app.input.csv.file}")
	private String inputCsv;

	@Value("${app.input.excel.file}")
	private String inputExcel;
	
	@Value("${app.output.excel.file}")
	private String outputExcel;
	
	@Value("${app.input.testsuite.name}")
	private String testSuiteName;
	
	@Value("${app.output.csv.file}")
	private String outputCsv;
	
	/**
	 * @return the driverType
	 */
	public String getDriverType() {
		return driverType;
	}

	/**
	 * @return the screenshotsDir
	 */
	public String getScreenshotsDir() {
		return screenshotsDir;
	}
	
	public int getTimeOutSeconds() {
		return Integer.parseInt(timeoutValue);
	}

	/**
	 * @return the inputCsv
	 */
	public String getInputCsv() {
		return inputCsv;
	}

	/**
	 * @return the outputCsv
	 */
	public String getOutputCsv() {
		return outputCsv;
	}

	/**
	 * @return the baseUrl
	 */
	public String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * @return the inputExcel
	 */
	public String getInputExcel() {
		return inputExcel;
	}

	/**
	 * @return the outputExcel
	 */
	public String getOutputExcel() {
		return outputExcel;
	}

	/**
	 * @return the testSuiteName
	 */
	public String getTestSuiteName() {
		return testSuiteName;
	}
	
}
