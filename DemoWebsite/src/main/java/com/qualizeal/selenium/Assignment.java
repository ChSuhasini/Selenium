package com.qualizeal.selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class Assignment {
	static WebDriver driver;

	public static void openURL() throws InterruptedException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/automation-practice-form");
		Thread.sleep(4000);
	}

	public static void setFirstName(String fName) throws InterruptedException {
		Thread.sleep(4000);
		WebElement fn = driver.findElement(By.xpath("//input[@placeholder='First Name']"));
		fn.sendKeys(fName);
	}

	public static void setLastName(String lName) {
     	WebElement ln = driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
		ln.sendKeys(lName);

	}

	public static void setEmail(String email) {
		WebElement em = driver.findElement(By.xpath("//input[@id='userEmail']"));
		em.sendKeys(email);
	}

	public static void setAddress(String address) {
		WebElement ad = driver.findElement(By.xpath("//textarea[@placeholder='Current Address']"));
		ad.sendKeys(address);
	}

	public static void setPhone(String phone) {
		WebElement pn = driver.findElement(By.xpath("//input[@placeholder='Mobile Number']"));
		pn.sendKeys(phone);
	}

	public static void setType(String gender) {
		WebElement g = driver.findElement(By.xpath("//label[contains(text(), '" + gender + "')]"));
		g.click();
	}

	public static void setSubjects(String subjects) {
		if (subjects == null || subjects.isEmpty()) {
			return;
		} else {
			WebElement sb = driver.findElement(By.xpath("//input[@id='subjectsInput']"));
			sb.sendKeys(subjects);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'subjects-auto-complete__option')]")));
			WebElement option = driver.findElement(By
					.xpath("//div[contains(@class, 'subjects-auto-complete__option') and text()='" + subjects + "']"));
			option.click();
		}
	}

	public static void setDateOfBirth(String day, String month, String year) {
		if (day.isEmpty() | month.isEmpty() | year.isEmpty()) {
			return;
		} else {
			WebElement dateOfBirthInput = driver.findElement(By.id("dateOfBirthInput"));
			dateOfBirthInput.click();

			Select monthDropdown = new Select(driver.findElement(By.className("react-datepicker__month-select")));
			monthDropdown.selectByVisibleText(month);

			Select yearDropdown = new Select(driver.findElement(By.className("react-datepicker__year-select")));
			yearDropdown.selectByVisibleText(year);

			WebElement selectDate = driver
					.findElement(By.xpath("//div[contains(@class, 'react-datepicker__day') and text()='" + day + "']"));
			selectDate.click();
		}

	}

	public static void setHobbies(String hobby) throws InterruptedException {
		List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		for (WebElement checkBox : checkboxes) {
			String checkboxId = checkBox.getAttribute("id");
			WebElement label = driver.findElement(By.xpath("//label[@for='" + checkboxId + "']"));
			String labelText = label.getText();
			if (labelText.equals(hobby)) {
				if (!checkBox.isSelected()) {
					label.click();
					break;
				}
			}
		}
	}

	public static void setStateAndCity(String stateName, String cityName) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		if (stateName.isEmpty()) {
			return;
		} else {

			WebElement stateDropdown = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[@class=' css-1wa3eu0-placeholder' and text()='Select State']")));
			scrollTo(stateDropdown);
			stateDropdown.click();
			WebElement stateOption = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//div[@tabindex='-1' and text()='" + stateName + "']")));
			scrollTo(stateOption);
			stateOption.click();
			Thread.sleep(2000);
		}

		if (stateName.isEmpty() | cityName.isEmpty()) {
			return;
		} else {
			WebElement cityDropdown = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[@class=' css-1wa3eu0-placeholder' and text()='Select City']")));
			scrollTo(cityDropdown);
			cityDropdown.click();
			WebElement cityOption = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//div[@tabindex='-1' and text()='" + cityName + "']")));
			scrollTo(cityOption);
			cityOption.click();
		}

	}

	public static void imageUpload(String filePath) {
		WebElement uploadFileButton = driver.findElement(By.xpath("//input[@id='uploadPicture']"));
		String userDir = System.getProperty("user.dir");
		String filePaths = userDir + filePath;
		uploadFileButton.sendKeys(filePaths);
	}

	public static void submitButton() {
		WebElement submit = driver.findElement(By.xpath("//button[@id='submit']"));
		scrollTo(submit);
		submit.click();
	}

	public static void closeBrowser() {
		driver.quit();
	}
	
	public static void scrollTo(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

}