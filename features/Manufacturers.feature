Feature: Manufacturers

Background: Below are common steps for every scenario
Given User launch chrome browser
When User opens URL "https://admin-demo.nopcommerce.com/login"
And User enters Email as "admin@yourstore.com" and Password as "admin"
And Click on Login
Then User can view Dashboard


Scenario: Add a New Manufacturer
When User click on catalog menu
And click on Manufacturers
And Click on Add New button
Then User can view add new Manufacturer page
When user enter Manufacturer info
And Click on Save button
Then User can view Manufacturer added confirmation message "The new manufacturer has been added successfully."
And close browser


Scenario: Search Manufacturer by Name
When User click on catalog menu
And click on Manufacturers
And Enter Manufacturer Name
When Click on Manufacturer Search button
Then User should found Manufacturer Name in the search table
And close browser