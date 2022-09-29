package com.build.stb.base;


import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;


public class Singleton {
	private static final Singleton singleton = new Singleton();
	private HashMap<String,List<String>> input;
	private WebDriver driver;
	private String siteName;
	private String browserName;
	private Singleton() {

	}
	
	/* Static 'instance' method */
	public static Singleton getInstance() {
		return singleton;

	}
	
	public void setInput(HashMap<String, List<String>> input) {
		this.input = input;
	}

	public HashMap<String, List<String>> getInput() {
		return input;
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	public void setWebSite(String siteName) {
		this.siteName = siteName;
	}
	
	public String getWebSite() {
		return siteName;
	}
	
	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}
	
	public String getbrowserName() {
		return browserName;
	}
	


}
