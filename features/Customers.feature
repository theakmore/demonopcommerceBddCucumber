Feature: Customers

Background: Below are common steps for every scenario
Given User launch chrome browser
When User opens URL "https://admin-demo.nopcommerce.com/login"
And User enters Email as "admin@yourstore.com" and Password as "admin"
And Click on Login
Then User can view Dashboard


Scenario: Add a New Customer
When User click on customers Menu
And Click on customers Menu Item
And Click on Add New button
Then User can view Add New Customer Page
When User enter customer Info
And Click on Save button
Then User can view confirmation message "The new customer has been added successfully."
And close browser


Scenario: Search Customer by EmailID
When User click on customers Menu
And Click on customers Menu Item
And Enter Customer Email
When Click on Search button
Then User should found Email in the search table
And close browser


Scenario: Search Customer by Name
When User click on customers Menu
And Click on customers Menu Item
And Enter Customer FirstName
And Enter Customer LastName
When Click on Search button
Then User should found Name in the search table
And close browser


