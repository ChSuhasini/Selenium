package com.qualizeal.selenium;
 
import java.time.Duration;
 
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
 
public class DemoTest {
 
    @BeforeMethod
    public void url() throws InterruptedException {
        Assignment.openURL();
    }
    @DataProvider(name = "dp")
    public Object[][] dataProvide() {  
     	ReadDataFromExcel d = new ReadDataFromExcel();
     	Object[][] s = d.readData();
     	return s;
     	
     }
 
    @Test(dataProvider = "dp")
    public void test(String fName, String lName, String email, String address, String phone, String subjects,String gender,
			String hobbies,String date, String month, String year,String state,String city,String filePath) throws InterruptedException {
        Assignment.setFirstName(fName);
        Assignment.setLastName(lName);
        Assignment.setEmail(email);
        Assignment.setAddress(address);
        Assignment.setPhone(phone);
        Assignment.setType(gender);       
        Assignment.setSubjects(subjects);
        Assignment.setHobbies(hobbies);
        Assignment.setDateOfBirth(date, month, year);
        Assignment.setStateAndCity(state, city);
        Assignment.imageUpload(filePath);
        Assignment.submitButton();
        Thread.sleep(3000);
        validateUsingAsserts(fName,lName,gender,phone);
  
    }
    
    public void validateUsingAsserts(String fName, String lName, String gender, String mobileNo) {

        if (fName.isEmpty()) {
            WebElement fNameField = Assignment.driver.findElement(By.id("firstName"));
            WebDriverWait wait = new WebDriverWait(Assignment.driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(fNameField));
            boolean isInvalid = fNameField.getCssValue("border-color").equals("rgb(220, 53, 69)");
            Assert.assertTrue(isInvalid, "First name is mandatory");
        }

        if (lName.isEmpty()) {
            WebElement lNameField = Assignment.driver.findElement(By.id("lastName"));
            WebDriverWait wait = new WebDriverWait(Assignment.driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(lNameField));
            boolean isInvalid = lNameField.getCssValue("border-color").equals("rgb(220, 53, 69)");
            Assert.assertTrue(isInvalid, "Last name is mandatory");
        }
 

        if (gender.isEmpty() || !(gender.equals("Male") || gender.equals("Female") || gender.equals("Other"))) {
            WebElement genderField = Assignment.driver.findElement(By.xpath("//label[text()='Male']"));
            String genderColor = genderField.getCssValue("color");
            Assert.assertEquals(genderColor, "rgba(220, 53, 69, 1)", "Gender is mandatory");
        }
 

        if (mobileNo.isEmpty() || !(mobileNo.length() == 10 || mobileNo.matches("^[1-9]\\d{9}$"))) {
            WebElement mobileNoField = Assignment.driver.findElement(By.id("userNumber"));
            WebDriverWait wait = new WebDriverWait(Assignment.driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(mobileNoField));
            boolean isInvalid = mobileNoField.getCssValue("border-color").equals("rgb(220, 53, 69)");
            Assert.assertTrue(isInvalid, "Mobile number is mandatory");
        }
    }
    
    @AfterMethod
    public void close() throws InterruptedException {
    
        Assignment.closeBrowser();
    }
 
 
    
 
 
}