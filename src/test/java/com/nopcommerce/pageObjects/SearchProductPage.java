package com.nopcommerce.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.nopcommerce.utilities.WaitHelper;

public class SearchProductPage {

	
	public WebDriver driver;
	WaitHelper waithelper;
	
	public SearchProductPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waithelper = new WaitHelper(driver);
		
	}
	
	
	
	@FindBy(how=How.XPATH , using="//input[@id='SearchProductName']")
	@CacheLookup
	WebElement txtProductName;
	
	@FindBy(how=How.XPATH , using="//button[@id='search-products']")
	@CacheLookup
	WebElement searchProductBtn;
	
	@FindBy(how=How.XPATH , using="//table[@id='products-grid']//tbody/tr")
	@CacheLookup
	List<WebElement> tableRows;
	
	@FindBy(how=How.XPATH , using="//table[@id='products-grid']//tbody/tr/td")
	@CacheLookup
	List<WebElement> tableColumns;
	
	
	public void enterProductName(String pname) {
		waithelper.WaitForElement(txtProductName, Duration.ofSeconds(30));
		txtProductName.clear();
		txtProductName.sendKeys(pname);
	}
	
	public void clicksearchProductBtn() {
		searchProductBtn.click();
	}
	
    public int getNoOfRows() {
    	return (tableRows.size());
    }
    
    public int getNoOfColumns() {
    	return (tableColumns.size());
    }
    
    public boolean searchProductByName(String name) {
    	boolean flag = false;
    	
    	for(int i=1;i<=getNoOfRows();i++) {
    		String Name = driver.findElement(By.xpath("//table[@id='products-grid']/tbody/tr[" + i + "]/td[3]")).getText();
    		System.out.println(Name);
    		if(Name.equalsIgnoreCase(name)) {
    			flag=true;
    			break;
    		}
    	}
    	
    	return flag;
    	
    	
    }
	
	
}
