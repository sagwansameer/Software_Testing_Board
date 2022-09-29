package com.build.stb.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class UtilTest {
	private Logger logger = LogManager.getLogger(UtilTest.class);

	/*
	 * @author Sameer Sagwan: This generic method is being used to enter the text in
	 * text box present in web page.
	 * 
	 */
	public void sendKeys(WebDriver dr, WebElement txtField, String txt) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(dr, 20);
		WebElement element = (WebElement) wait.until(ExpectedConditions.visibilityOf(txtField));
		logger.info(element + " this is after explixit wait ");
		Reporter.log(element + " this is after explixit wait ");
		moveToElement(dr, element);
		element.clear();
		element.sendKeys(txt);
		logger.info(txt + " is entered in the text box");
		Reporter.log(txt + " is entered in the text box");

	}
	/*
	 * @author Sameer Sagwan: This generic method is being used to click on any
	 * element present in web page using some expected condition (Element to be
	 * click able).
	 * 
	 */

	public void click(WebDriver dr, WebElement element) {

		WebDriverWait wait = new WebDriverWait(dr, 20);
		WebElement e = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(element));
		logger.info(element + " this is after explixit wait ");
		Reporter.log(element + " this is after explixit wait ");
		moveToElement(dr, e);
		e.click();
		Reporter.log("User Clicked on " + element);

	}
	/*
	 * @author Sameer Sagwan: This generic method is being used to click on any
	 * element present in web page without using any condition.
	 * 
	 */

	public void clickWithoutWait(WebDriver dr, WebElement element) {
		element.click();

	}

	/*
	 * @author Sameer Sagwan: This generic method is being used to perform mouse
	 * hover action on the menu links.
	 * 
	 */
	public void mouseHover(WebDriver dr, WebElement element) {
		Actions actions = new Actions(dr);
		actions.moveToElement(element).perform();

	}

	/*
	 * @author Sameer Sagwan: This generic method is being used to select the
	 * product element and accepts the value which number of item needs to be
	 * selected.
	 * 
	 */
	public void selectProduct(WebDriver dr, int productNumber) {
		List<WebElement> list = dr.findElements(By
				.xpath("//div[@class='products wrapper grid products-grid']//li[@class='item product product-item']"));
		for (int i = 0; i < list.size(); i++) {
			logger.info(list.get(i).getTagName());
			Reporter.log(list.get(i).getTagName());
		}
		list.get(productNumber).click();

	}

	/*
	 * @author Sameer Sagwan: This generic method is being used to select the size
	 * element and accepts the value which size of item needs to be selected.
	 * 
	 */
	public void selectSize(WebDriver dr, String Size) {

		List<WebElement> list = dr.findElements(By.xpath("//div[@class='swatch-attribute size']//div"));
		for (int i = 0; i < list.size(); i++) {
			String str = list.get(i).getText();
			logger.info("Available size is :  " + str);
			Reporter.log("Available  size :  " + str);
			if (str.equalsIgnoreCase(Size)) {

				list.get(i).click();
				logger.info("Selected size is :  " + str);
				Reporter.log("Selected  size is :  " + str);
				break;

			}

		}

	}

	/*
	 * @author Sameer Sagwan: This generic method is being used to select the color
	 * for the product
	 * 
	 */
	public void selectColour(WebDriver dr) {

		List<WebElement> list = dr.findElements(By.xpath("//div[@class='swatch-attribute color']//div/div"));
		list.get(1).click();

	}

	/*
	 * @author Sameer Sagwan: This generic method is being used to get the element
	 * into the view to perform the action on it.
	 * 
	 */
	public void moveToElement(WebDriver dr, WebElement element) {
		((JavascriptExecutor) dr).executeScript("arguments[0].scrollIntoView(true);", element);
		logger.info(element + " is scrolled into view");
		Reporter.log(element + " is scrolled into view");
	}

	/*
	 * @author Sameer Sagwan: This generic method is being used to select element
	 * from the drop down.
	 * 
	 */
	public void dropDown(WebDriver dr, WebElement dropDownElement, String data) {

		WebElement droplist = dropDownElement;
		droplist.sendKeys(data);
	}

	/*
	 * @author Sameer Sagwan: This generic method is being used to check if the
	 * given element is available or not and return the status false if the element
	 * is not available.
	 * 
	 */
	public boolean isElementavailable(WebDriver dr, String element) {

		boolean status = false;
		try {
			dr.findElement(By.xpath(element));
			status = true;
			return status;

			/*
			 * if (element.isEnabled() && element.isDisplayed()) { status = true; return
			 * status; } else { status = false; return status; }
			 */
		} catch (Exception e) {
			logger.error(element + " is not available on the screen");
			Reporter.log(element + " is not available on the screen");

			status = false;
			return status;
		}
	}
	/*
	 * @author Sameer Sagwan: This generic method is being used to fetch the
	 * response of APi(That gives the user details) and convert it into a hash map
	 * and return it for the further process
	 * 
	 */

	public HashMap<String, List<String>> inputData() throws IOException {
		HashMap<String, List<String>> inputData = new HashMap<String, List<String>>();
		int numberOfUser = 2;
		String url = "https://randomuser.me/api/1.4/";

		RestAssured.baseURI = url;
		Response response = RestAssured.given().when().get("?results=" + numberOfUser + "&nat=US").then().extract()
				.response();
		JsonPath jsonPathEvaluator = response.body().jsonPath();

		String fisrtName = jsonPathEvaluator.get("results[0].name.first").toString();
		String lastName = jsonPathEvaluator.get("results[0].name.last").toString();
		String address = "Street no.-  " + jsonPathEvaluator.get("results[0].location.street.number").toString() + ", "
				+ jsonPathEvaluator.get("results[0].location.street.name").toString() + ", City -"
				+ jsonPathEvaluator.get("results[0].location.city")
				+ jsonPathEvaluator.get("results[0].location.state");
		String city = jsonPathEvaluator.get("results[0].location.city");
		String state = jsonPathEvaluator.get("results[0].location.state");
		String pinCode = Integer.toString(jsonPathEvaluator.get("results[0].location.postcode"));
		String phoneNumber = jsonPathEvaluator.get("results[0].phone");
		String SSN = jsonPathEvaluator.get("results[0].id.value");
		String userName = jsonPathEvaluator.get("results[0].login.username");
		String password = jsonPathEvaluator.get("results[0].login.password");
		String email = jsonPathEvaluator.get("results[0].email");
		String gender = jsonPathEvaluator.get("results[0].gender");
		String country = jsonPathEvaluator.get("results[0].location.country");
		String company = jsonPathEvaluator.get("results[0].name.first").toString() + " Group of Industries, "
				+ jsonPathEvaluator.get("results[0].location.state");
		List<String> data = new ArrayList<>();
		data.add(fisrtName);
		data.add(lastName);
		data.add(address);
		data.add(city);
		data.add(state);
		data.add(pinCode);
		data.add(phoneNumber);
		data.add(SSN);
		data.add(userName);
		data.add(password);
		data.add(email);
		data.add(gender);
		data.add(company);
		data.add(country);

		inputData.put("user", data);

		return inputData;

	}
	/*
	 * @author Sameer Sagwan: This generic method is being used to capture the
	 * screen for the failure and the success both.
	 * 
	 */

	public void takeScreen(String name, WebDriver dr) throws IOException {
		try {

			logger.info("Take ScreenShot::" + name);
			Reporter.log("Take ScreenShot::" + name);
			String timeStamp = new SimpleDateFormat("dd_MM_yyyy_HH.mm.ss").format(new java.util.Date());
			File srcFile = ((TakesScreenshot) dr).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile,
					new File(System.getProperty("user.dir") + "\\screenshots\\" + timeStamp + "_" + name + ".jpg"),
					true);

		} catch (Exception e) {
			String str = e.getMessage();
			logger.error("Screenshot is not being captured due to below error");
			logger.error(str);
			Reporter.log("Screenshot is not being captured due to below error");
			Reporter.log(str);

		}

	}

}
