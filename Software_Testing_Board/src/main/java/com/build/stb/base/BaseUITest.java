package com.build.stb.base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;


import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseUITest extends BaseStrings {
	protected WebDriver driver;
	Logger logger= LogManager.getLogger(BaseUITest.class);
	

	/*
	 * @author Sameer Sagwan: This generic method is being used launch the web
	 * driver as per need and return the driver reference.
	 * 
	 */
	public WebDriver initialization() throws IOException {
		String browser=Singleton.getInstance().getbrowserName();

		if (browser.equalsIgnoreCase("chrome")) {
			Reporter.log("Automation will be running on Browser :" + browser);
			logger.info("Automation will be running on Browser :" + browser);
			WebDriverManager.chromedriver().setup();
			this.driver = new ChromeDriver();
			Reporter.log(browser+" Browser has been launched");
			logger.info(browser+" Browser has been launched");
			driver.manage().window().maximize();
			Reporter.log(browser+" Browser has been Maximized");
			logger.info(browser+" Browser has been Maximized");
			driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
			driver.navigate().to(Singleton.getInstance().getWebSite());
			return driver;
		} else {
			Reporter.log("Automation will be running on Browser :" + browser);
			logger.info("Automation will be running on Browser :" + browser);
			//System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			this.driver = new FirefoxDriver();
			Reporter.log(browser+" Browser has been launched");
			logger.info(browser+" Browser has been launched");
			driver.manage().window().maximize();
			Reporter.log(browser+" Browser has been Maximized");
			logger.info(browser+" Browser has been Maximized");
			driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.MINUTES);
			driver.navigate().to(Singleton.getInstance().getWebSite());
			return driver;
		}
	}

}
