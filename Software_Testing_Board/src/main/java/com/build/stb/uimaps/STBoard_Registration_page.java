package com.build.stb.uimaps;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.build.stb.base.BaseStrings;


public class STBoard_Registration_page extends BaseStrings{

	// private WebDriver driver;
	
	//final String packageName="";
	public STBoard_Registration_page(WebDriver driver) {

		// PageFactory.initElements(driver, this);
		 //this.driver = driver;
		 PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath = "//ul[@class='header links']")
	public WebElement createAnAccountLink;
	
	@FindBy(xpath = "//*[contains(text(),'First Name:')]")
	public WebElement demo;
	@FindBy(xpath = "//input[@id='firstname']")
	public WebElement firstName;
	@FindBy(xpath = "//input[@id='lastname']")
	public WebElement lastName;
	@FindBy(xpath = "//input[@id='email_address']")
	public WebElement email;
	@FindBy(xpath = "//input[@id='customer.address.street']")
	public WebElement address;
	@FindBy(xpath = "//input[@id='customer.address.city']")
	public WebElement city;
	@FindBy(xpath = "//input[@id='customer.address.state']")
	public WebElement state;
	@FindBy(xpath = "//input[@id='customer.address.zipCode']")
	public WebElement zipCode;
	@FindBy(xpath = "//input[@id='customer.phoneNumber']")
	public WebElement phoneNumber;
	@FindBy(xpath = "//input[@id='customer.ssn']")
	public WebElement ssn;
	@FindBy(xpath = "//input[@id='customer.username']")
	public WebElement userName;
	@FindBy(xpath = "//input[@id='password']")
	public WebElement password;
	@FindBy(xpath = "//input[@id='password-confirmation']")
	public WebElement confirmPassword;
	@FindBy(xpath = "//button[@title='Create an Account']")
	public WebElement createAccountButton;
	@FindBy(xpath = "//*[text()[contains(.,'There is already')]]")
	public WebElement duplicateError;
	@FindBy(xpath = "//div[@class='page messages']")
	public WebElement message;
	
	

}
