package com.build.stb.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;


import com.build.stb.base.BaseStrings;
import com.build.stb.base.BaseUITest;
import com.build.stb.base.Singleton;
import com.build.stb.base.UtilTest;
import com.build.stb.uimaps.STBoard_Registration_page;

import groovyjarjarantlr4.v4.parse.ANTLRParser.throwsSpec_return;

public class STBoard_Registration_test extends BaseStrings {

	BaseUITest bu = new BaseUITest();
	STBoard_Registration_page registerPage;
	UtilTest util = new UtilTest();
	Logger logger = LogManager.getLogger(STBoard_Registration_test.class);

	public void userRegistration(WebDriver driver) throws InterruptedException, IOException {

		HashMap<String, List<String>> inputData = Singleton.getInstance().getInput();
		List<String> ldata = inputData.get("user");

		registerPage = new STBoard_Registration_page(driver);
		List<WebElement> r = registerPage.createAnAccountLink.findElements(By.tagName("a"));
		for (WebElement element : r) {
			String str = element.getText();
			logger.info(str);
			Reporter.log(str);
			if (str.contains("Create an Account")) {
				element.click();
				break;

			}

		}
		// List<String> ldata = inputData.get("user");
		util.sendKeys(driver, registerPage.firstName, ldata.get(0));
		util.sendKeys(driver, registerPage.lastName, ldata.get(1));
		//util.sendKeys(driver, registerPage.email, ldata.get(10));
		util.sendKeys(driver, registerPage.email, "test00011@gmail.com");
		
		
		
		logger.info(ldata.get(10));
		Reporter.log(ldata.get(10));
		util.sendKeys(driver, registerPage.password, ldata.get(9) + password);
		logger.info(ldata.get(9) + password);
		Reporter.log(ldata.get(9) + password);
		util.sendKeys(driver, registerPage.confirmPassword, ldata.get(9) + password);
		util.click(driver, registerPage.createAccountButton);
		Thread.sleep(1000);

		message(driver, ldata);

	}

	public void message(WebDriver driver, List<String> ldata) throws InterruptedException {
		List<WebElement> msg = registerPage.message.findElements(By.tagName("div"));
		
		for (WebElement element : msg) {
			String ls = element.getText();
			
			logger.info("Getting this value  " + ls);
			if (ls.contains(errorMessage)) {
				logger.info("If Worked    " + ls);
				
				Reporter.log("If Worked    " + ls);
				logger.info("To be Registered    " +ldata.get(10));
				util.sendKeys(driver, registerPage.email,"#123"+ldata.get(10));
				util.sendKeys(driver, registerPage.password, ldata.get(9) + password);
				logger.info(ldata.get(9) + password);
				Reporter.log(ldata.get(9) + password);
				util.sendKeys(driver, registerPage.confirmPassword, ldata.get(9) + password);
				util.click(driver, registerPage.createAccountButton);
				Thread.sleep(1000);

				break;

			} else if (ls.contains(successMessage)) {
				logger.info("If Else worked  " + ls);
				Reporter.log("If Else worked  " + ls);
				break;
			} /*
				 * else { logger.info("Final Else Worked   " + ls);
				 * Reporter.log("Final Else Worked   " + ls); break; }
				 */

		}
	}

}