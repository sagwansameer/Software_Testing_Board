package com.build.stb.uimaps;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.build.stb.base.BaseStrings;


public class STBoard_Product_Page extends BaseStrings{

	// private WebDriver driver;
	
	//final String packageName="";
	public STBoard_Product_Page(WebDriver driver) {

		// PageFactory.initElements(driver, this);
		 //this.driver = driver;
		 PageFactory.initElements(driver, this);

	}
	
	
	@FindBy(xpath = "//a[@id='ui-id-5']")
	public WebElement manSection;
	@FindBy(xpath = "//a[@id='ui-id-4']")
	public WebElement womanSection;
	@FindBy(xpath = "//a[@id='ui-id-17']")
	public WebElement manTop;
	@FindBy(xpath = "//a[@id='ui-id-9']")
	public WebElement womanTop;
	@FindBy(xpath = "//a[@id='ui-id-19']")
	public WebElement manJacket;
	@FindBy(xpath = "//a[@id='ui-id-11']")
	public WebElement womanJacket;
	@FindBy(xpath = "//div[@class='products wrapper grid products-grid']")
	public WebElement productList;
	@FindBy(xpath = "//div[@class='page-title-wrapper product']//span")
	public WebElement productNameOnDetailPage;
	@FindBy(xpath = "//div[@class='swatch-attribute size']")
	public WebElement sizeOnDetailPage;
	@FindBy(xpath = "//div[@class='swatch-attribute color']")
	public WebElement colourOnDetailPage;
	
	
	
	@FindBy(xpath = "//input[@id='qty']")
	public WebElement quantity;
	@FindBy(xpath = "//button[@id='product-addtocart-button']")
	public WebElement addToCart;
	@FindBy(xpath = "//span[@class='counter-number']")
	public WebElement cartIcon;
	@FindBy(xpath = "//button[@id='top-cart-btn-checkout']")
	public WebElement proceedToCheckOut;
	@FindBy(xpath = "//input[@name='company']")
	public WebElement companyName;
	@FindBy(xpath = "//input[@name='street[0]']")
	public WebElement street1;
	@FindBy(xpath = "//input[@name='street[1]']")
	public WebElement street2;
	@FindBy(xpath = "//input[@name='street[2]']")
	public WebElement street3;
	@FindBy(xpath = "//input[@name='city']")
	public WebElement cityInOrder;
	@FindBy(xpath = "//select[@name='region_id']")
	public WebElement stateDropdown;
	@FindBy(xpath = "//input[@name='postcode']")
	public WebElement zipCodeAddress;
	@FindBy(xpath = "//input[@name='telephone']")
	public WebElement phoneNumberAddress;
	@FindBy(xpath = "//td[@id='label_carrier_bestway_tablerate']")
	public WebElement selectPriceBestWay;
	@FindBy(xpath = "//td[@id='label_carrier_flatrate_flatrate']")
	public WebElement selectPriceFlatRate;
	@FindBy(xpath = "//span[contains(text(),'Next')]")
	public WebElement nextButton;
	@FindBy(xpath = "//span[contains(text(),'Place Order')]")
	public WebElement PlaceOrder;
	@FindBy(xpath = "//a[@class='order-number']")
	public WebElement orderID;
	@FindBy(xpath = "//td[@data-th='Product Name']")
	public WebElement productOnOrderScreen;
	
	
	//
	//
	

}
