package com.nopcommerce.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.nopcommerce.utilities.WaitHelper;

public class SearchManufacturerPage {
	public WebDriver driver;
	WaitHelper waithelper;
	
	public SearchManufacturerPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waithelper = new WaitHelper(driver);
		
	}
	
	
	
	@FindBy(how=How.XPATH , using="//input[@id='SearchManufacturerName']")
	@CacheLookup
	WebElement txtManufacturerName;
	
	@FindBy(how=How.XPATH , using="//button[@id='search-manufacturers']")
	@CacheLookup
	WebElement searchManufacturersBtn;
	
	@FindBy(how=How.XPATH , using="//table[@id='manufacturers-grid']//tbody/tr")
	@CacheLookup
	List<WebElement> tableRows;
	
	@FindBy(how=How.XPATH , using="//table[@id='manufacturers-grid']//tbody/tr/td")
	@CacheLookup
	List<WebElement> tableColumns;
	
	public void enterManufacturerName(String mname) {
		txtManufacturerName.sendKeys(mname);
	}
	
	public void clickOnManufacturerSearchBtn() {
		searchManufacturersBtn.click();
	}
	
	
    public int getNoOfRows() {
    	return (tableRows.size());
    }
    
    public int getNoOfColumns() {
    	return (tableColumns.size());
    }
    
    public boolean searchManufacturerByName(String name) {
    	boolean flag = false;
    	
    	for(int i=1;i<=getNoOfRows();i++) {
    		String Name = driver.findElement(By.xpath("//table[@id='manufacturers-grid']/tbody/tr[" + i + "]/td[2]")).getText();
    		System.out.println(Name);
    		if(Name.equalsIgnoreCase(name)) {
    			flag=true;
    			break;
    		}
    	}
    	
    	return flag;
    	
    	
    }

}
