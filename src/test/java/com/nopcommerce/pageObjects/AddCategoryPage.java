package com.nopcommerce.pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nopcommerce.utilities.WaitHelper;

public class AddCategoryPage {
	
	
	public WebDriver driver;
	WaitHelper waithelper;
	
	public AddCategoryPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waithelper = new WaitHelper(driver);
	}

	
	@FindBy(xpath="//p[text()=' Categories']")
	@CacheLookup
	WebElement Categories;
	
	@FindBy(xpath="//input[@id='Name']")
	@CacheLookup
	WebElement txtCategoryName;
	
	@FindBy(xpath="//body[@id='tinymce']")
	@CacheLookup
	WebElement categoryDescription;
	
	public void clickOnCategorymenu() {
		waithelper.WaitForElement(Categories, Duration.ofSeconds(30));
		Categories.click();
	}
	
	public void setCategoryName(String cname) {
		txtCategoryName.sendKeys(cname);
	}
	
	public void addCategoryDescription(String desc) {
		driver.switchTo().frame("Description_ifr");
		categoryDescription.sendKeys(desc);
		driver.switchTo().defaultContent();
	}

}
