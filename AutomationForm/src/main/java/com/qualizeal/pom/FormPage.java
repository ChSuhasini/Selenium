package com.qualizeal.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qualizeal.utils.SeleniumHelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.util.List;

public class FormPage {

	private WebDriver driver;
	private SeleniumHelpers seleniumHelpers;

	public FormPage(WebDriver driver) {
		this.driver = driver; 
		this.seleniumHelpers = new SeleniumHelpers(driver);
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='First Name']")
	private WebElement firstNameField;

	@FindBy(xpath = "//input[@placeholder='Last Name']")
	private WebElement lastNameField;

	@FindBy(xpath = "//input[@id='userEmail']")
	private WebElement emailField;

	@FindBy(xpath = "//textarea[@placeholder='Current Address']")
	private WebElement addressField;

	@FindBy(xpath = "//input[@placeholder='Mobile Number']")
	private WebElement phoneField;

	@FindBy(xpath = "//input[@id='subjectsInput']")
	private WebElement subjectsInputField;

	@FindBy(xpath = "//input[@id='dateOfBirthInput']")
	private WebElement dateOfBirthInput;

	@FindBy(xpath = "//input[@id='uploadPicture']")
	private WebElement uploadPictureButton;

	@FindBy(xpath = "//button[@id='submit']")
	private WebElement submitButton;

	public void openURL(String url) throws InterruptedException {
		driver.get(url);
		driver.manage().window().maximize();
		Thread.sleep(4000);
	}

	public void setFirstName(String fName) {
		seleniumHelpers.sendDataTextBox(firstNameField,fName);
	}

	public void setLastName(String lName) {
		seleniumHelpers.sendDataTextBox(lastNameField,lName);
	}

	public void setEmail(String email) {
		seleniumHelpers.sendDataTextBox(emailField,email);
	}

	public void setAddress(String address) {
		seleniumHelpers.sendDataTextBox(addressField,address);
	}

	public void setPhone(String phone) {
		seleniumHelpers.sendDataTextBox(phoneField,phone);
	}

	public void setType(String gender) {
		WebElement genderOption = driver.findElement(By.xpath("//label[contains(text(), '" + gender + "')]"));
		seleniumHelpers.scrollTo(genderOption);
		seleniumHelpers.clickElement(genderOption);
	}

	public void setSubjects(String subjects) {
		if (subjects != null && !subjects.isEmpty()) {
			seleniumHelpers.sendDataTextBox(subjectsInputField,subjects);
			seleniumHelpers.waitUntilVisibility("//div[contains(@class, 'subjects-auto-complete__option')]");
			WebElement option = driver.findElement(By
					.xpath("//div[contains(@class, 'subjects-auto-complete__option') and text()='" + subjects + "']"));
			seleniumHelpers.clickElement(option);
		}
	}

	public void setDateOfBirth(String day, String month, String year) {
		if (!day.isEmpty() && !month.isEmpty() && !year.isEmpty()) {
			seleniumHelpers.clickElement(dateOfBirthInput);

			Select monthDropdown = new Select(driver.findElement(By.className("react-datepicker__month-select")));
			monthDropdown.selectByVisibleText(month);

			Select yearDropdown = new Select(driver.findElement(By.className("react-datepicker__year-select")));
			yearDropdown.selectByVisibleText(year);

			WebElement selectDate = driver
					.findElement(By.xpath("//div[contains(@class, 'react-datepicker__day') and text()='" + day + "']"));
			seleniumHelpers.clickElement(selectDate);
		}
	}

	public void setHobbies(String hobby) {
		List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		for (WebElement checkBox : checkboxes) {
			String checkboxId = checkBox.getAttribute("id");
			WebElement label = driver.findElement(By.xpath("//label[@for='" + checkboxId + "']"));
			String labelText = label.getText();
			if (labelText.equals(hobby) && !checkBox.isSelected()) {
				seleniumHelpers.clickElement(label);
				break;
			}
		}
	}

	
	public void imageUpload(String filePath) {
		seleniumHelpers.fileUpload(uploadPictureButton, filePath);
	}

	public void submitForm() {
		seleniumHelpers.scrollTo(submitButton);
		seleniumHelpers.clickElement(submitButton);
	}

	public void closeBrowser() {
		driver.quit();
	}


}

