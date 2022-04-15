package in.jeani;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import in.jeani.drivers.DriverFactory;
import in.jeani.tests.GenericTests;

@Component
public class AppRunner implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppRunner.class);
	
	@Autowired
	DriverFactory driverFactory;
	
	@Autowired
	GenericTests tests;
	
	private WebDriver driver;
	
	@Override
	public void run(String... args) throws Exception {
		fetchDriver();
		testAll();
		// exitDriver();
	}
	
	private void fetchDriver() {
		driver = driverFactory.getDriver();
		LOGGER.debug("Selenium Driver chosen: " + driver.manage());
	}
	private void exitDriver() {
		driver.quit();
	}
	private void testAll() {
		tests.init(driver);
		tests.testFromCsv();
		exitDriver();
	}

}
