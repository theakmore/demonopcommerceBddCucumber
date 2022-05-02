package com.nopcommerce.stepDefinitions;

import java.io.FileInputStream;

import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.nopcommerce.pageObjects.AddCategoryPage;
import com.nopcommerce.pageObjects.AddManufacturerPage;
import com.nopcommerce.pageObjects.AddProductPage;
import com.nopcommerce.pageObjects.AddcustomerPage;
import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.pageObjects.SearchCategoryPage;
import com.nopcommerce.pageObjects.SearchCustomerPage;
import com.nopcommerce.pageObjects.SearchManufacturerPage;
import com.nopcommerce.pageObjects.SearchProductPage;

public class Steps extends BaseClass{
	
	// cucumber @Before hook
	
	@Before   
	public void setup() throws Exception {
		
		//Logger steps
		logger = Logger.getLogger("nopCommerce"); //added logger
		PropertyConfigurator.configure("log4j.properties");	
		 logger.setLevel(Level.DEBUG);
		//Logger steps done
		 
		 
		//Loading Config.properties file steps
		configProp = new Properties();
		FileInputStream configPropFile = new FileInputStream("config.properties");
		configProp.load(configPropFile);
		//Loading Config.properties file is done
	
        String br=configProp.getProperty("browser");

        //Check if browser passed from config.properties file is 'firefox'
        
        if (br.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver",configProp.getProperty("firefoxpath"));
            driver = new FirefoxDriver();
        }
        
      //Check if browser passed from config.properties file is 'chrome'

        else if (br.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver",configProp.getProperty("chromepath"));
            driver = new ChromeDriver();
        }

      //Check if browser passed from config.properties file is 'ie'
        
        else if (br.equals("ie")) {
            System.setProperty("webdriver.ie.driver",configProp.getProperty("iepath"));
            driver = new InternetExplorerDriver();
        }
	
		
	}
	
	// cucumber @After hook
	
	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			// take screenshot:
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);

		}
	}
	
	
	@Given("User launch chrome browser")
	public void user_launch_chrome_browser() {

		logger.info("*********launching the Browser*************");
		lp = new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		logger.info("*********Opening URL*************");
	    driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		logger.info("********* Proving user info ***************");
		lp.setUserName(email);
		lp.setPassword(password);

	}

	@When("Click on Login")
	public void click_on_login() throws InterruptedException {
	   lp.clickLogin();
	   Thread.sleep(5000);
	}

	@Then("page title should be {string}")
	public void page_title_should_be(String title) throws InterruptedException {
	
		if(driver.getPageSource().contains("Login was unsuccessful.")) {
			logger.info("*********Login Failed ***************");
			driver.close();
			Assert.assertTrue(false);
		}
		else {
			logger.info("*********Login successfull ***************");
			Assert.assertEquals(title, driver.getTitle());
		}
		Thread.sleep(5000);
	}

	@When("User click on Logout link")
	public void user_click_on_logout_link() throws InterruptedException {
		logger.info("*********Logout from application***************");
	   Thread.sleep(5000);
	   lp.clickLogout();
	   Thread.sleep(5000);
	}

	@Then("close browser")
	public void close_browser() {
	 logger.info("*********Closing application ***************");
	  driver.quit();
	}
	
	//Customer feauture step definitions...............
	
	@Then("User can view Dashboard")
	public void user_can_view_dashboard() {
		
	   logger.info("********* Adding Customer Scenario started  ***************");	
	   addCust = new AddcustomerPage(driver);
	   Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
	   
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
	 
		Thread.sleep(5000);
		addCust.clickOnCustomersMenu();
		
	}

	@When("Click on customers Menu Item")
	public void click_on_customers_menu_item() throws InterruptedException {
		
		Thread.sleep(2000);
		addCust.clickOnCustomersMenuItem();
	}

	@When("Click on Add New button")
	public void click_on_add_new_button() throws InterruptedException {
	
		Thread.sleep(2000);
		addCust.clickOnAddNew();
		Thread.sleep(2000);
	}

	@Then("User can view Add New Customer Page")
	public void user_can_view_add_new_customer_page() {
	   
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}

	@When("User enter customer Info")
	public void user_enter_customer_info() throws InterruptedException {
	  
		logger.info("********* Providing Customer details ***************");
		String email = randomString() + "@gmail.com";
		addCust.setEmail(email);
		addCust.setPassword("test123");
	       // Registered - default
        // The customer cannot be in both 'Guests' and 'Registered' customer roles
        // Add the customer to 'Guests' or 'Registered' customer role
		
		addCust.setCustomerRoles("Vendors");
		Thread.sleep(3000);
		addCust.setManagerOfVendor("Vendor 2");
	    addCust.setGender("Male");
        addCust.setFirstName("Rajat");
        addCust.setLastName("Joshi");
        addCust.setDob("7/05/1998"); // Format: D/MM/YYYY
        addCust.setCompanyName("teamart");
        addCust.setAdminComment("This is for testing.........");
			
		
	}

	@When("Click on Save button")
	public void click_on_save_button() {
	    addCust.clickOnSave();
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String string) {
		
		logger.info("********* Add customer validation ***************");
		 Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
	                .contains("The new customer has been added successfully."));
		
	}
	
	// Steps for searchig Customer by emailid

	
	@When("Enter Customer Email")
	public void enter_customer_email() {
		
	    logger.info("********* Search Customer by Email ID Scenario started ***************");
		searchCust = new SearchCustomerPage(driver);
		searchCust.setEmail("admin@yourStore.com");
	}

	@When("Click on Search button")
	public void click_on_search_button() throws InterruptedException {
		searchCust.clickSearch();
		Thread.sleep(5000);
	}

	@Then("User should found Email in the search table")
	public void user_should_found_email_in_the_search_table() {
	   
		logger.info("********* Search customer by email validation ***************");
		boolean status = searchCust.searchCustomerByEmail("admin@yourStore.com");
		Assert.assertEquals(true, status);
		
	}
	
	// Steps for searchig Customer by using FirstName and LastName
	
	@When("Enter Customer FirstName")
	public void enter_customer_first_name() {
		
		logger.info("********* Seqarch custoemr by Name Scenario started ***************");
		searchCust = new SearchCustomerPage(driver);
		searchCust.setFirstName("John");
	}
	@When("Enter Customer LastName")
	public void enter_customer_last_name() {
		
		logger.info("********* Providing customer name ***************");
		searchCust = new SearchCustomerPage(driver);
		searchCust.setLastName("Smith");
	}
	@Then("User should found Name in the search table")
	public void user_should_found_name_in_the_search_table() {
		   
		logger.info("********* Search customer by name validation ***************");
		boolean status = searchCust.searchCustomerByName("John Smith");
		Assert.assertEquals(true, status);
		
	}
	
	//Product feauture step definitions..
	
	@When("User click on catalog menu")
	public void user_click_on_catalog_menu() {

		addprod = new AddProductPage(driver);
		addprod.clickOnCatalogMenu();
	}

	@When("click on products")
	public void click_on_products() {
		addprod.clickOnProducts();
	}


	@Then("User can view add new product page")
	public void user_can_view_add_new_product_page() {
		Assert.assertEquals("Add a new product / nopCommerce administration", addCust.getPageTitle());
	}

	@When("user enter product info")
	public void user_enter_product_info() {
		
		logger.info("********* Providing Customer details ***************");
		addprod.setProductName("Apple watch");
		addprod.addproductShortDescription("The Ultimate Apple Sports Watch");
		addprod.addproductFullDescription("The Apple Watch Series 3 is a sleek accessory that's a must-have if you're"
				+ " all about staying fit. The watch features an enhanced Heart Rate app, \r\n"
				+ "and a built-in altimeter. Also carry and listen to your favourite songs on your wrist.\r\n"
				+ " Equipped with Siri, this smartwatch makes being active and staying connected enjoyable.");
		
		addprod.selectCategory("Electronics >> Others");
		addprod.selectManufacturer("Apple");
		addprod.selectVendor("Vendor 1");
	    
	}
	

	@Then("User can view product added confirmation message {string}")
	public void user_can_view_product_added_confirmation_message(String string) {
		logger.info("********* Add Product validation ***************");
		 Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
	                .contains("The new product has been added successfully."));
	}
	
	
	
	// Steps for searchig Product by using Product Name
	
	@When("Enter Product Name")
	public void enter_product_name() {

		searchprod = new SearchProductPage(driver);
		searchprod.enterProductName("Windows 8 Pro");
	}

	@When("Click on Product Search button")
	public void click_on_product_search_button() {

		searchprod.clicksearchProductBtn();
	}

	@Then("User should found Product Name in the search table")
	public void user_should_found_product_name_in_the_search_table() {
	
		logger.info("********* Search Product by name validation ***************");
		boolean status = searchprod.searchProductByName("Windows 8 Pro");
		Assert.assertEquals(true, status);
	}
	
	// Category step definitions....
	
	@When("click on Categories")
	public void click_on_categories() {

		addcatg = new AddCategoryPage(driver);
		addcatg.clickOnCategorymenu();
		
	}

	@Then("User can view add new Category page")
	public void user_can_view_add_new_category_page() {
		Assert.assertEquals("Add a new category / nopCommerce administration", addCust.getPageTitle());
	}

	@When("user enter category info")
	public void user_enter_category_info() {
		addcatg.setCategoryName("Kids-toys");
		addcatg.addCategoryDescription("This category contains all type kid-toys.");
	}

	@Then("User can view category added confirmation message {string}")
	public void user_can_view_category_added_confirmation_message(String string) {

		logger.info("********* Add Category validation ***************");
		 Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
	                .contains("The new category has been added successfully."));
	}
	
	// Steps for searchig Category by  Name
	
	@When("Enter Category Name")
	public void enter_category_name() {
		searchcatg = new SearchCategoryPage(driver);
		searchcatg.enterCategoryName("Computers");
	}

	@When("Click on Category Search button")
	public void click_on_category_search_button() {

		searchcatg.clickOnCategorySearchBtn();
	}

	@Then("User should found Category Name in the search table")
	public void user_should_found_category_name_in_the_search_table() {

		logger.info("********* Search Category by name validation ***************");
		boolean status = searchcatg.searchCategoryByName("Computers");
		Assert.assertEquals(true, status);
	}
	
	
	// Manufacturers step definitions....
	
	@When("click on Manufacturers")
	public void click_on_manufacturers() {
		addmanufac = new AddManufacturerPage(driver);
		addmanufac.clickOnManufacturersmenu();
	}

	@Then("User can view add new Manufacturer page")
	public void user_can_view_add_new_manufacturer_page() {
		Assert.assertEquals("Add a new manufacturer / nopCommerce administration", addCust.getPageTitle());
	}

	@When("user enter Manufacturer info")
	public void user_enter_manufacturer_info() {
	  
		addmanufac.setManufacturersName("Puma");
		addmanufac.addManufacturerDescription("Puma SE, branded as Puma, is a German multinational corporation that "
				+ "designs and manufactures athletic and casual footwear, apparel and accessories, "
				+ "which is headquartered in Herzogenaurach, Bavaria, Germany. "
				+ "Puma is the third largest sportswear manufacturer in the world.");
	}

	@Then("User can view Manufacturer added confirmation message {string}")
	public void user_can_view_manufacturer_added_confirmation_message(String string) {
		logger.info("********* Add Manufacturer validation ***************");
		 Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
	                .contains("The new manufacturer has been added successfully."));
	}
	
	// Steps for searchig Manufacturer by  Name
	
	@When("Enter Manufacturer Name")
	public void enter_manufacturer_name() {
		searchmanufac = new SearchManufacturerPage(driver);
		searchmanufac.enterManufacturerName("Nike");
	}

	@When("Click on Manufacturer Search button")
	public void click_on_manufacturer_search_button() {
		searchmanufac.clickOnManufacturerSearchBtn();
	}

	@Then("User should found Manufacturer Name in the search table")
	public void user_should_found_manufacturer_name_in_the_search_table() {

		logger.info("********* Search Manufacturer by name validation ***************");
		boolean status = searchmanufac.searchManufacturerByName("Nike");
		Assert.assertEquals(true, status);
	}
	
	

}
