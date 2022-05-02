Feature: Categories

Background: Below are common steps for every scenario
Given User launch chrome browser
When User opens URL "https://admin-demo.nopcommerce.com/login"
And User enters Email as "admin@yourstore.com" and Password as "admin"
And Click on Login
Then User can view Dashboard


Scenario: Add a New Category
When User click on catalog menu
And click on Categories
And Click on Add New button
Then User can view add new Category page
When user enter category info
And Click on Save button
Then User can view category added confirmation message "The new category has been added successfully."
And close browser


Scenario: Search Category by Name
When User click on catalog menu
And click on Categories
And Enter Category Name
When Click on Category Search button
Then User should found Category Name in the search table
And close browser
