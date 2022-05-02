package com.nopcommerce.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddProductPage {
	
	public WebDriver driver;
	
	public AddProductPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@href='#']//p[contains(text(),'Catalog')]")
	@CacheLookup
	WebElement Catalogmenu;
	
	@FindBy(xpath="//p[text()=' Products']")
	@CacheLookup
	WebElement products;
	
	@FindBy(xpath="//input[@id='Name']")
	@CacheLookup
	WebElement txtProductName;
	
	@FindBy(xpath="//textarea[@id='ShortDescription']")
	@CacheLookup
	WebElement productShortDescription;
	
	@FindBy(xpath="//body[@id='tinymce']")
	@CacheLookup
	WebElement productFullDescription;
	
	@FindBy(xpath="//ul[@id='SelectedCategoryIds_taglist']/parent::div")
	@CacheLookup
	WebElement selectProductCategory;
	
	@FindBy(xpath="//ul[@id='SelectedCategoryIds_listbox']//li")
	@CacheLookup
	List<WebElement> allProductCategoryOptions;
	
	@FindBy(xpath="//ul[@id='SelectedManufacturerIds_taglist']/parent::div")
	@CacheLookup
	WebElement selectManufacturer;
	
	@FindBy(xpath="//ul[@id='SelectedManufacturerIds_listbox']//li")
	@CacheLookup
	List<WebElement> allManufacturerOptions;	
	
	@FindBy(xpath="//select[@id='VendorId']")
	@CacheLookup
	WebElement selectVendor;
	
	
	public void clickOnCatalogMenu() {
		Catalogmenu.click();
	}
	
	public void clickOnProducts() {
		products.click();
	}
	
	public void setProductName(String pname) {
		txtProductName.sendKeys(pname);
	}
	
	public void addproductShortDescription(String desc) {
		productShortDescription.sendKeys(desc);
	}
	
	public void addproductFullDescription(String fdesc) {
		driver.switchTo().frame("FullDescription_ifr");
		productFullDescription.sendKeys(fdesc);
		driver.switchTo().defaultContent();
	}
	
	// selecting the Category-dropdown without using select class
	
	public void selectCategory(String value) {
		
		selectProductCategory.click();
        System.out.println(allProductCategoryOptions.size());
                       
        for(int i = 0; i<=allProductCategoryOptions.size()-1; i++) {
  
            if(allProductCategoryOptions.get(i).getText().contains(value)) {
                 
            	allProductCategoryOptions.get(i).click();
                break;
                 
            }
        }

	}
	
	// selecting the Manufacturer-dropdown without using select class
	
	public void selectManufacturer(String value) {
		selectManufacturer.click();
        System.out.println(allManufacturerOptions.size());
                       
        for(int i = 0; i<=allManufacturerOptions.size()-1; i++) {
  
            if(allManufacturerOptions.get(i).getText().contains(value)) {
                 
            	allManufacturerOptions.get(i).click();
                break;
                 
            }
        }
	}
	
	// selecting the dropdown  using select class
	
	public void selectVendor(String value) {
		Select select = new Select(selectVendor);
		select.selectByVisibleText(value);
	}

}
