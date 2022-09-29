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
	

	public void sendKeys(WebDriver dr, WebElement txtField, String txt) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(dr, 20);
		WebElement e = (WebElement) wait.until(ExpectedConditions.visibilityOf(txtField));
		Reporter.log(txtField + " this is after explixit wait ");
		moveToElement(dr, e);
		// e.click();
		e.clear();
		e.sendKeys(txt);
		Reporter.log(txt + " has been entered in " + txtField);

	}

	public void click(WebDriver dr, WebElement element) {

		WebDriverWait wait = new WebDriverWait(dr, 20);
		// WebElement elementt=(WebElement)
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
		WebElement e = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(element));
		Reporter.log(element + " this is after explixit wait ");
		moveToElement(dr, e);
		e.click();
		Reporter.log("User Clicked on " + element);

	}

	public void clickWithoutWait(WebDriver dr, WebElement element) {
		element.click();

	}
	public void mouseHover(WebDriver dr, WebElement element) {
		Actions actions = new Actions(dr);
		actions.moveToElement(element).perform();
        
	}
	public void selectProduct(WebDriver dr,int productNumber) {
		//List<WebElement> list= dr.findElements(By.xpath("//span[@class='product-image-container']"));
		List<WebElement> list= dr.findElements(By.xpath("//div[@class='products wrapper grid products-grid']//li[@class='item product product-item']"));
for (int i = 0; i <list.size(); i++) {
			
			System.out.println(list.get(i).getTagName());
			
		}
		list.get(productNumber).click();
		
		
	}
	public void selectSize(WebDriver dr,String Size) {
		
		List<WebElement> list= dr.findElements(By.xpath("//div[@class='swatch-attribute size']//div"));
		for (int i = 0; i <list.size(); i++) {
			
			System.out.println(list.get(i).getText());
			if (list.get(i).getText().equalsIgnoreCase(Size)) {
				
				list.get(i).click();
				System.out.println("Clicked on size::  "+list.get(i).getText());
				break;
				
			}
			
		}
		
		
		
	}

	public void selectColour(WebDriver dr) {
		
		List<WebElement> list= dr.findElements(By.xpath("//div[@class='swatch-attribute color']//div/div"));
		list.get(1).click();
		/*
		 * for (int i = 0; i <list.size(); i++) {
		 * System.out.println(list.get(i).getAttribute("aria-label"));
		 * 
		 * 
		 * if (list.get(i).getAttribute("aria-label").equalsIgnoreCase(colour)) {
		 * list.get(i).click(); break; }
		 * 
		 * 
		 * }
		 */
		
		
		
	}

	public void moveToElement(WebDriver dr, WebElement element) {
		((JavascriptExecutor) dr).executeScript("arguments[0].scrollIntoView(true);", element);
		Reporter.log(element + " is scrolled into view");
	}

	public void dropDown(WebDriver dr, WebElement dropDownElement, String data) {

		WebElement droplist = dropDownElement;
		droplist.sendKeys(data);
	}

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

			status = false;
			return status;
		}
	}
	public HashMap<String, List<String>> inputData() throws IOException {
		HashMap<String, List<String>> inputData = new HashMap<String, List<String>>();
		int numberOfUser = 2;
		String url = "https://randomuser.me/api/1.4/";

		RestAssured.baseURI = url;
		Response response = RestAssured.given().when().get("?results=" + numberOfUser + "&nat=US").then().extract()
				.response();

		/*
		 * // List<String> jsonResponse = ); System.out.println(response.jsonPath());
		 * 
		 * System.out.println(response.body().asString());
		 * System.out.println("____________________________________________________");
		 */
		JsonPath jsonPathEvaluator = response.body().jsonPath();
		
			String fisrtName = jsonPathEvaluator.get("results[0].name.first").toString();
			String lastName = jsonPathEvaluator.get("results[0].name.last").toString();
			String address = "Street no.-  "
					+ jsonPathEvaluator.get("results[0].location.street.number").toString() + ", "
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
			String country=jsonPathEvaluator.get("results[0].location.country");
			String company=jsonPathEvaluator.get("results[0].name.first").toString()+" Group of Industries, "+jsonPathEvaluator.get("results[0].location.state");
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

	public void takeScreen(String name, WebDriver dr) throws IOException {
		try {

			Reporter.log("Take ScreenShot::" + name);
			String timeStamp = new SimpleDateFormat("dd_MM_yyyy_HH.mm.ss").format(new java.util.Date());
			// String timeStampFolder = singleton.getTime();
			File srcFile = ((TakesScreenshot) dr).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile,
					new File(System.getProperty("user.dir") + "\\screenshots\\" + timeStamp + "_" + name + ".jpg"),
					true);

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}

	public boolean getObject(WebDriver dr, WebElement element) {

		boolean status = false;
		try {
			if (element.isEnabled() && element.isDisplayed()) {
				status = true;
				return status;
			} else {
				status = false;
				return status;
			}
		} catch (Exception e) {

			status = false;
			return status;
		}

	}

}
