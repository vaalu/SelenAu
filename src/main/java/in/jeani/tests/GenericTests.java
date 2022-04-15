package in.jeani.tests;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.jeani.tests.utils.IndividualTest;
import in.jeani.tests.utils.ScreenTest;
import in.jeani.utils.CSVUtils;
import in.jeani.utils.ExcelUtils;
import in.jeani.utils.PropertiesUtil;

@Component
public class GenericTests extends ScreenTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GenericTests.class);

	@Autowired
	PropertiesUtil props;
	
	@Autowired
	CSVUtils csvUtils;
	
	@Autowired
	ExcelUtils excelUtils;
	
	public void init(WebDriver driver) {
		setDriver(driver);
		init();
		driver.get(props.getBaseUrl());
	}
	
	public void test() {
		List<IndividualTest> tests = excelUtils.readInputFile();
		performTests(tests);
	}
	public void testFromCsv() {
		List<IndividualTest> tests = csvUtils.readInputCSVFile();
		performTests(tests);
	}
	public void performTests(List<IndividualTest> tests) {
		
		for (IndividualTest test : tests) {
			takeScreenshot(test.getName());
			long timeStart = System.currentTimeMillis();
			try {
				if(test.getType() != null) {
					test.setAssertionResult(true);
					WebElement element = getWebElement(test);
					LOGGER.info("Expected result: " + test.getExpectedResult());
					if(test.getExpectedResult() != null && !test.getExpectedResult().isBlank()) {
						if(test.getExpectedResult().equalsIgnoreCase(element.getText())) {
							LOGGER.debug(test.getExpectedResult() + " : PASSED");
							test.setAssertionResult(true);
						} else {
							LOGGER.debug(test.getExpectedResult() + " : DID NOT QUALIFY : " + test.getComment());
							test.setActualResult(test.getExpectedResult() + " : DID NOT QUALIFY : " + test.getComment());
							test.setAssertionResult(false);
						}
					} else {
						LOGGER.debug(test.getName() + " : PASSED");
						test.setAssertionResult(true);
					}
					if(test.getInputValue() != null) {
						useInput(element, test);
					}
					if(test.getAction() != null) {
						performAction(element, test);
					}
					takeScreenshot(test.getName());
				}
			} catch (Exception e) {
				test.setAssertionResult(false);
				LOGGER.debug(test.getScreenItem() + " : DID NOT QUALIFY : " + test.getComment());
				test.setActualResult(test.getScreenItem() + " : DID NOT QUALIFY : " + test.getComment());
			}
			long timeEnd = System.currentTimeMillis();
			long totalDuration = timeEnd - timeStart;
			test.setDurationOfTest(totalDuration);
			excelUtils.writeInputFile(props.getTestSuiteName(), tests);
		}
	}
}
