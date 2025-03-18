package com.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.stepdefinitions"
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {

}
