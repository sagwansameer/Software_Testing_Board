package com.build.stb.base;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;

/*
 * @author Sameer Sagwan: This generic class is being used as the database where we can store the value and can be fetched further.
 * 
 */

public class Singleton {
	private static final Singleton singleton = new Singleton();
	private HashMap<String, List<String>> input;
	private WebDriver driver;
	private String siteName;
	private String browserName;

	/*
	 * @author Sameer Sagwan: Constructor of this class is private so that only one
	 * object will be created for this class.
	 */
	private Singleton() {

	}

	/*
	 * @author Sameer Sagwan: This public method is defined as public to access the
	 * object of the class. Only this method will help us to create the object of
	 * this class.
	 */
	public static Singleton getInstance() {
		return singleton;

	}

	/*
	 * @author Sameer Sagwan: This method will be used to set the value that we have
	 * fetched using the API.
	 */
	public void setInput(HashMap<String, List<String>> input) {
		this.input = input;
	}

	/*
	 * @author Sameer Sagwan: This method will be used to get the value that we have
	 * fetched using the API.
	 */
	public HashMap<String, List<String>> getInput() {
		return input;
	}

	/*
	 * @author Sameer Sagwan: This method will be used to set the value of web
	 * driver.
	 */
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	/*
	 * @author Sameer Sagwan: This method will be used to get the value of web
	 * driver.
	 */
	public WebDriver getDriver() {
		return driver;
	}

	/*
	 * @author Sameer Sagwan: This method will be used to set the value of web site
	 * name.
	 */
	public void setWebSite(String siteName) {
		this.siteName = siteName;
	}

	/*
	 * @author Sameer Sagwan: This method will be used to get the value of web site
	 * name.
	 */

	public String getWebSite() {
		return siteName;
	}

	/*
	 * @author Sameer Sagwan: This method will be used to set the browser name.
	 */
	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	/*
	 * @author Sameer Sagwan: This method will be used to get the browser name.
	 */
	public String getbrowserName() {
		return browserName;
	}

}
