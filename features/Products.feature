Feature: Products

Background: Below are common steps for every scenario
Given User launch chrome browser
When User opens URL "https://admin-demo.nopcommerce.com/login"
And User enters Email as "admin@yourstore.com" and Password as "admin"
And Click on Login
Then User can view Dashboard

Scenario: Add a New Product
When User click on catalog menu
And click on products
And Click on Add New button
Then User can view add new product page
When user enter product info
And Click on Save button
Then User can view product added confirmation message "The new product has been added successfully."
And close browser


Scenario: Search Product by Name
When User click on catalog menu
And click on products
And Enter Product Name
When Click on Product Search button
Then User should found Product Name in the search table
And close browser