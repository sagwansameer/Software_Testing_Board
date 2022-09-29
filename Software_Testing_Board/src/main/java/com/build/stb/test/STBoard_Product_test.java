package com.build.stb.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.build.stb.base.BaseStrings;
import com.build.stb.base.BaseUITest;
import com.build.stb.base.Singleton;
import com.build.stb.base.UtilTest;
import com.build.stb.uimaps.STBoard_Product_Page;

public class STBoard_Product_test extends BaseStrings {
	WebDriver driver;
	BaseUITest bu = new BaseUITest();
	STBoard_Product_Page product;
	UtilTest util = new UtilTest();
	private Singleton singleton = Singleton.getInstance();
	private String productName;
	HashMap<String, List<String>> inputData;
	List<String> ldata;
	Logger logger = LogManager.getLogger(STBoard_Product_test.class);

	/*
	 * @author Sameer Sagwan: This will execute before executing any test case, we
	 * are fetching the input from the testng.xml and fetching the user details from
	 * the API and creating a hashmap of input to traverse the data for further
	 * execution.
	 * 
	 */
	@BeforeTest
	@Parameters({ "BrowserName", "SiteName" })
	public void befortest(@Optional String browserName, String siteName) throws IOException, InterruptedException {

		logger.info("BeforTest annautation is triggered");
		Thread.sleep(2000);
		inputData = util.inputData();
		ldata = inputData.get("user");
		logger.info(inputData.get("user").toString());
		singleton.setInput(inputData);
		singleton.setBrowserName(browserName);
		singleton.setWebSite(siteName);

	}

	/*
	 * @author Sameer Sagwan: This will execute before every test case, here we just
	 * initialize the web driver and creating the object of page class to use the
	 * locators.
	 * 
	 */
	@BeforeMethod
	public void brforMethod() throws IOException {
		logger.info("Befor Method annautation is triggered");
		this.driver = bu.initialization();
		singleton.setDriver(driver);
		product = new STBoard_Product_Page(driver);

	}

	/*
	 * @author Sameer Sagwan: This is the main test case to register the user on the
	 * portal, searching the product as per gender(we are getting in API) and make
	 * final payments and check if the order is generated or not.
	 * 
	 */
	@Test(testName = "purchaseProduct", description = "This is the testcase used to search and purchase the product as per gender of User")
	public void purchaseProduct() throws InterruptedException, IOException {
		STBoard_Registration_test registration = new STBoard_Registration_test();
		registration.userRegistration(driver);
		if (ldata.get(11).equalsIgnoreCase("male")) {
			Thread.sleep(500);
			util.mouseHover(driver, product.manSection);
			logger.info("Man's products is being searched");
			Reporter.log("Man's products is being searched");

			Thread.sleep(500);
			util.mouseHover(driver, product.manTop);
			Thread.sleep(500);
			util.clickWithoutWait(driver, product.manJacket);
		}

		else {
			Thread.sleep(500);
			util.mouseHover(driver, product.womanSection);
			Thread.sleep(500);
			System.out.println("else Mouse Hover Worked");
			util.mouseHover(driver, product.womanTop);
			Thread.sleep(500);
			util.clickWithoutWait(driver, product.womanJacket);
		}

		selectProduct(driver, 1);
		productName = product.productNameOnDetailPage.getText();
		selectSize(driver, "M");
		selectColor(driver);
		util.sendKeys(driver, product.quantity, "1");
		util.click(driver, product.addToCart);
		Thread.sleep(2000);
		util.click(driver, product.cartIcon);
		Thread.sleep(2000);
		util.click(driver, product.proceedToCheckOut);
		util.sendKeys(driver, product.companyName, ldata.get(12));
		util.sendKeys(driver, product.street1, ldata.get(2));
		util.sendKeys(driver, product.street2, ldata.get(3));
		util.sendKeys(driver, product.street3, ldata.get(4));
		util.sendKeys(driver, product.cityInOrder, ldata.get(3));
		util.dropDown(driver, product.stateDropdown, ldata.get(4));
		util.sendKeys(driver, product.zipCodeAddress, ldata.get(5));
		util.sendKeys(driver, product.phoneNumberAddress, ldata.get(6));
		util.click(driver, product.selectPriceBestWay);
		util.click(driver, product.nextButton);
		util.click(driver, product.PlaceOrder);
		util.click(driver, product.orderID);
		String orderedProduct = product.productOnOrderScreen.findElement(By.tagName("strong")).getText();
		logger.info("Ordered Product :  " + orderedProduct);
		Reporter.log("Ordered Product :  " + orderedProduct);
		if (productName.equalsIgnoreCase(orderedProduct)) {

			logger.info("Testcase is Passed");
			Reporter.log("Testcase is Passed");
		}

	}

	/*
	 * @author Sameer Sagwan: This generic method is used to select the product from
	 * the product screen.
	 * 
	 */

	public void selectProduct(WebDriver dr, int productNumber) {

		List<WebElement> list = product.productList.findElements(By.tagName("li"));
		list.get(productNumber).click();

	}

	/*
	 * @author Sameer Sagwan: This generic method is used to select size for the
	 * product.
	 */
	public void selectSize(WebDriver dr, String Size) {

		List<WebElement> list = product.sizeOnDetailPage.findElements(By.tagName("div"));
		for (int i = 0; i < list.size(); i++) {
			String str = list.get(i).getText();
			if (str.equalsIgnoreCase(Size)) {
				list.get(i).click();
				logger.info("Clicked on size::  " + str);
				Reporter.log("Clicked on size::  " + str);
				break;

			}

		}

	}

	/*
	 * @author Sameer Sagwan: This generic method is used to select color for the
	 * product.
	 */
	public void selectColor(WebDriver dr) {

		List<WebElement> list = product.colourOnDetailPage.findElements(By.tagName("div"));
		String str = list.get(2).getAttribute("aria-label");
		logger.info(" selected color " + str);
		Reporter.log(" selected color " + str);
		list.get(2).click();

	}

	/*
	 * @author Sameer Sagwan: This will execute after every test case. We are
	 * checking the status of the test case run, If the test case is passed a
	 * screenshot is captured with success status.
	 * 
	 */
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException, InterruptedException {

		Thread.sleep(1000);
		logger.info("After Method annautation os running");
		Reporter.log("After Method annautation os running");

		logger.info(result.getName());
		Reporter.log(result.getName());

		logger.info(result.getStatus());
		Reporter.log(Integer.toString(result.getStatus()));

		System.out.println();
		if (result.getStatus() == 2) {
			logger.info("Testcase is failed , Now Itest Case will be executed");
			Reporter.log("Testcase is failed , Now Itest Case will be executed");

		} else {
			util.takeScreen("Passed" + result.getName(), singleton.getDriver());
			singleton.getDriver().close();

		}
	}

}