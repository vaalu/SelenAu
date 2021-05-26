/**
 * 
 */
package in.jeani.tests.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.jeani.utils.PropertiesUtil;

/**
 * @author mohanavelp
 *
 */
@Component
public class ScreenTest extends AbstractTest {

	@Autowired
	PropertiesUtil props;
	
	WebDriverWait wait;
	int timeoutSeconds;
	
	@Override
	public void setDriver(WebDriver driver) {
		super.setDriver(driver);
		timeoutSeconds = props.getTimeOutSeconds();
		wait = new WebDriverWait(driver, timeoutSeconds);
	}
	
	public WebElement waitForCssClassNameResource(String cssClassName) {
		return this.wait.until(ExpectedConditions.presenceOfElementLocated(By.className(cssClassName)));
	}
	
	public WebElement waitForCssSelectorResource(String cssSelector) {
		return this.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
	}
	
	public WebElement waitForXPathResource(String xpath) {
		return this.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
	}
	
	public WebElement getWebElement(IndividualTest test) {
		
		WebElement element = null;
		String type = test.getType();
		String path = test.getSelector();
		if (type != null && path != null) {
			if(type.equalsIgnoreCase("xpath")) {
				element = this.waitForXPathResource(path);
			} else if(type.equalsIgnoreCase("css")) {
				element = this.waitForXPathResource(path);
			} else if(type.equalsIgnoreCase("cssSelector")) {
				element = this.waitForXPathResource(path);
			} 
		}
		return element;
		
	}
	
	public WebElement useInput(WebElement element, IndividualTest test) {
		if(test.getAction() != null) {
			if(test.getAction().equalsIgnoreCase("send" )) {
				element.sendKeys(test.getInputValue());
			}
		}
		return element;
	}
	
	public void performAction(WebElement element, IndividualTest test) {
		if(test.getAction() != null) {
			if(test.getAction().equalsIgnoreCase("click" )) {
				element.click();
			}
			else if(test.getAction().equalsIgnoreCase("hover" )) {
				Actions actions = new Actions(getDriver());
				actions.moveToElement(element).perform();
			}
		}
	}
	
}
