package com.qualizeal.utils;
 
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class SeleniumHelpers {
	private WebDriver driver; 
	private WebDriverWait wait;
	
	public SeleniumHelpers(WebDriver driver) { 
		this.driver = driver; 
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
	}
	
	public  void sendDataTextBox(WebElement element, String text) {
        element.clear();  
        element.sendKeys(text); 
    }
 
 
    public  void clickElement(WebElement element) {
        element.click(); 
    }
    
 
    public  WebElement findElementByXpath(String xpath) {
        return driver.findElement(By.xpath(xpath));  
    }
 
 
    public  boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();  
    }
 
    public  String getElementText(WebElement element) {
        return element.getText();  
    }


    public  void scrollTo(WebElement element) {
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    	
    }
	public  void selectFromDropdownByText(WebElement userRoleDropdown, String role) {

		Select sel = new Select(userRoleDropdown);
		sel.selectByVisibleText(role);

	}
	
	public  void fileUpload(WebElement fileUpload,String filePath) {
		String userDir = System.getProperty("user.dir");
		String fullPath = userDir + filePath;
		fileUpload.sendKeys(fullPath);
		
	}


	public  String getTextFromElement(WebDriver driver, String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		WebElement element = driver.findElement(By.xpath(xpath)); 
		return element.getText();  
	}


	public  boolean isElementDisplayed(WebDriver driver, String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			WebElement element = driver.findElement(By.xpath(xpath));
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public void waitUntilVisibility(String xpath) {
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(xpath)));
	}
    
    
}