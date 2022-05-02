package com.nopcommerce.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


	

public class AddcustomerPage {
	
	public WebDriver driver;
	
	public AddcustomerPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By lnkCustomers_menu = By.xpath("//a[@href='#']//p[contains(text(),'Customers')]");
	By lnkCustomers_menuitem = By.xpath("//a[@class='nav-link' and @href='/Admin/Customer/List']/child::p[1]");
	
	By btnAddnew = By.xpath("//a[@class='btn btn-primary']");
	By txtEmail = By.xpath("//input[@id='Email']");
	By txtPassword = By.xpath("//input[@id='Password']");
	
	By txtcustomerRoles = By.xpath("//div[@class='input-group-append input-group-required']/child::div/child::div/child::div");
	
	
	By lstitemAdministrators = By.xpath("//li[text()='Administrators']");
	By lstitemRegistered = By.xpath("//li[text()='Registered']");
	By lstitemGuests = By.xpath("//li[text()='Guests']");
	By lstitemVendors = By.xpath("//li[text()='Vendors']");
	
	By drpmgrVendor = By.xpath("//select[@id='VendorId']");
	By rdMaleGender = By.xpath("//input[@id='Gender_Male']");
	By rdFeMaleGender = By.xpath("//input[@id='Gender_Female']");
	
	By txtFirstName = By.xpath("//input[@id='FirstName']");
	By txtLastName= By.xpath("//input[@id='LastName']");
	
	By txtDob = By.xpath("//input[@id='DateOfBirth']");
	By txtCompanyName = By.xpath("//input[@id='Company']");
	By txtAdminComment = By.xpath("//textarea[@id='AdminComment']");
	By btnSave = By.xpath("//button[@name='save']");
	
	
	// Action Methods
	
    public String getPageTitle()
    {
        return driver.getTitle();
    }
	
	public void clickOnCustomersMenu() {
		driver.findElement(lnkCustomers_menu).click();
	}
	
	public void clickOnCustomersMenuItem() {
		driver.findElement(lnkCustomers_menuitem).click();
	}
	
	public void clickOnAddNew() {
		driver.findElement(btnAddnew).click();
	}
	
	public void setEmail(String email) {
		driver.findElement(txtEmail).sendKeys(email);
	}
	
	public void setPassword(String password) {
		driver.findElement(txtPassword).sendKeys(password);
	}
	
	public void setCustomerRoles(String role) throws InterruptedException 
	
	{	
		driver.findElement(txtcustomerRoles).click();
		
		WebElement listitem;
		
		Thread.sleep(5000);
		
		if(role.equals("Administrators")) {
			listitem = driver.findElement(lstitemAdministrators);
		}
		else if(role.equals("Guests")) {
			listitem = driver.findElement(lstitemGuests);
		}
		else if(role.equals("Registered")) {
			listitem = driver.findElement(lstitemRegistered);
		}
		else if(role.equals("Vendors")) {
			listitem = driver.findElement(lstitemVendors);
		}
		else {
			listitem = driver.findElement(lstitemGuests);
		}
		
//		listitem.click(); // if this is not working we can use Javascriptexecutor to click as following
//		
//		Thread.sleep(5000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", listitem);
		
		
	 }
	
	public void setManagerOfVendor(String value) {
		Select drp = new Select(driver.findElement(drpmgrVendor));
		drp.selectByVisibleText(value);
	}
	
	public void setGender(String gender) {
		if(gender.equals("Male")) {
			driver.findElement(rdMaleGender).click();
		}
		else if(gender.equals("Female")){
			driver.findElement(rdFeMaleGender).click();
		}
		else {
			driver.findElement(rdMaleGender).click(); //default
		}
	}
	
	
   public void setFirstName(String fname)
    {
        driver.findElement(txtFirstName).sendKeys(fname);
    }

    public void setLastName(String lname)
    {
        driver.findElement(txtLastName).sendKeys(lname);
    }

    public void setDob(String dob)
    {
        driver.findElement(txtDob).sendKeys(dob);
    }

    public void setCompanyName(String comname)
    {
        driver.findElement(txtCompanyName).sendKeys(comname);
    }

    public void setAdminComment(String comment)
    {
        driver.findElement(txtAdminComment).sendKeys(comment);
    }
	

    public void clickOnSave()
    {
        driver.findElement(btnSave).click();
    }
	

}
