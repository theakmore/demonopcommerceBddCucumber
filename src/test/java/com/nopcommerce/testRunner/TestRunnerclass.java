package com.nopcommerce.testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features= { ".//features/" }, 
		glue="com.nopcommerce.stepDefinitions" ,
		dryRun=false , // true this will cross check every featurefilesteps contains corresponding stepdefinition or not 
		monochrome=true , //will remove unnecessary characters
		plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"  } 
//		tags = "@sanity"  //to run the specific scenarios
		)
public class TestRunnerclass {
	
	

}
