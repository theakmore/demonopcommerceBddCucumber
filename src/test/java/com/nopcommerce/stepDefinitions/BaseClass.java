package com.nopcommerce.stepDefinitions;

import java.util.Properties;


import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.nopcommerce.pageObjects.AddCategoryPage;
import com.nopcommerce.pageObjects.AddManufacturerPage;
import com.nopcommerce.pageObjects.AddProductPage;
import com.nopcommerce.pageObjects.AddcustomerPage;
import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.pageObjects.SearchCategoryPage;
import com.nopcommerce.pageObjects.SearchCustomerPage;
import com.nopcommerce.pageObjects.SearchManufacturerPage;
import com.nopcommerce.pageObjects.SearchProductPage;

public class BaseClass {
	
	public WebDriver driver;
	public LoginPage lp;
	public AddcustomerPage addCust;
	public SearchCustomerPage searchCust;
	public AddProductPage addprod;
	public SearchProductPage searchprod;
	public AddCategoryPage addcatg;
	public SearchCategoryPage searchcatg;
	public AddManufacturerPage addmanufac;
	public SearchManufacturerPage searchmanufac;
	public static Logger logger;
	public Properties configProp;
	
	// Created for generating random String for unique email
	public static String randomString() {
		String generatedstring1 = RandomStringUtils.randomAlphabetic(5);
		return (generatedstring1);
	}

}
