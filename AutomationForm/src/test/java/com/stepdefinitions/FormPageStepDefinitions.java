package com.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.qualizeal.pom.FormPage;

public class FormPageStepDefinitions {

    WebDriver driver;
    FormPage formPage;

    @Given("User navigates to the form page")
    public void user_navigates_to_the_form_page() throws InterruptedException {
        driver = new ChromeDriver();
        formPage = new FormPage(driver);
        formPage.openURL("https://demoqa.com/automation-practice-form");
    }

    @When("User enters first name {string}")
    public void user_enters_first_name(String firstName) {
        formPage.setFirstName(firstName);
    }

    @When("User enters last name {string}")
    public void user_enters_last_name(String lastName) {
        formPage.setLastName(lastName);
    }

    @When("User enters email {string}")
    public void user_enters_email(String email) {
        formPage.setEmail(email);
    }

    @When("User enters phone number {string}")
    public void user_enters_phone_number(String phone) {
        formPage.setPhone(phone);
    }

    @When("User selects gender {string}")
    public void user_selects_gender(String gender) {
        formPage.setType(gender);
    }

    @When("User enters subject {string}")
    public void user_enters_subject(String subject) {
        formPage.setSubjects(subject);
    }

    @When("User selects date of birth {string}{string}{string}")
    public void user_selects_date_of_birth(String day, String month, String year) {
        formPage.setDateOfBirth(day, month, year);
    }

    @When("User selects hobby {string}")
    public void user_selects_hobby(String hobby) {
        formPage.setHobbies(hobby);
    }


    @When("User uploads image {string}")
    public void user_uploads_image(String filePath) {
        formPage.imageUpload(filePath);
    }

    @When("User submits the form")
    public void user_submits_the_form() {
        formPage.submitForm();
    }

    @Then("User closes the browser")
    public void user_closes_the_browser() {
        formPage.closeBrowser();
    }
}
