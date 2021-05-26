/**
 * 
 */
package in.jeani.tests.utils;

/**
 * @author mohanavelp
 *
 */
public class IndividualTest {

	private String name;
	private String type;
	private String screenItem;
	private String selector;
	private String action;
	private String inputValue;
	private String expectedResult;
	private String comment;
	private String actualResult;
	private boolean assertionResult;
	private long durationOfTest;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the selector
	 */
	public String getSelector() {
		return selector;
	}
	/**
	 * @param selector the selector to set
	 */
	public void setSelector(String selector) {
		this.selector = selector;
	}
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return the inputValue
	 */
	public String getInputValue() {
		return inputValue;
	}
	/**
	 * @param inputValue the inputValue to set
	 */
	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}
	/**
	 * @return the expectedResult
	 */
	public String getExpectedResult() {
		return expectedResult;
	}
	/**
	 * @param expectedResult the expectedResult to set
	 */
	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}
	/**
	 * @return the actualResult
	 */
	public String getActualResult() {
		return actualResult;
	}
	/**
	 * @param actualResult the actualResult to set
	 */
	public void setActualResult(String actualResult) {
		this.actualResult = actualResult;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Name: ").append(getName())
		.append("\t Type: ").append(getType())
		.append("\t Selector: ").append(getSelector())
		.append("\t Input: ").append(getInputValue())
		.append("\t Action: ").append(getAction())
		.append("\t Expected Result: ").append(getExpectedResult())
		.append("\t Comment: ").append(getComment());		
		return builder.toString();
	}
	/**
	 * @return the screenItem
	 */
	public String getScreenItem() {
		return screenItem;
	}
	/**
	 * @param screenItem the screenItem to set
	 */
	public void setScreenItem(String screenItem) {
		this.screenItem = screenItem;
	}
	/**
	 * @return the assertionResult
	 */
	public boolean isAssertionResult() {
		return assertionResult;
	}
	/**
	 * @param assertionResult the assertionResult to set
	 */
	public void setAssertionResult(boolean assertionResult) {
		this.assertionResult = assertionResult;
	}
	/**
	 * @return the durationOfTest
	 */
	public long getDurationOfTest() {
		return durationOfTest;
	}
	/**
	 * @param durationOfTest the durationOfTest to set
	 */
	public void setDurationOfTest(long durationOfTest) {
		this.durationOfTest = durationOfTest;
	}
	
}
