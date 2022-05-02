package com.nopcommerce.pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nopcommerce.utilities.WaitHelper;

public class AddManufacturerPage {
	
	public WebDriver driver;
	WaitHelper waithelper;
	
	public AddManufacturerPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waithelper = new WaitHelper(driver);
	}

	
	@FindBy(xpath="//p[text()=' Manufacturers']")
	@CacheLookup
	WebElement Manufacturers;
	
	@FindBy(xpath="//input[@id='Name']")
	@CacheLookup
	WebElement txtManufacturerName;
	
	
	@FindBy(xpath="//body[@id='tinymce']")
	@CacheLookup
	WebElement ManufacturerDescription;
	
	public void clickOnManufacturersmenu() {
		waithelper.WaitForElement(Manufacturers, Duration.ofSeconds(30));
		Manufacturers.click();
	}
	
	public void setManufacturersName(String mname) {
		txtManufacturerName.sendKeys(mname);
	}
	
	public void addManufacturerDescription(String desc) {
		driver.switchTo().frame("Description_ifr");
		ManufacturerDescription.sendKeys(desc);
		driver.switchTo().defaultContent();
	}

}
