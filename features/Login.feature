Feature: Login


Scenario: Successfull Login with Valid credentials

Given User launch chrome browser
When User opens URL "https://admin-demo.nopcommerce.com/login"
And User enters Email as "admin@yourstore.com" and Password as "admin"
And Click on Login
Then page title should be "Dashboard / nopCommerce administration"
When User click on Logout link
Then page title should be "Your store. Login"
And close browser


Scenario Outline: Login Data Driven

Given User launch chrome browser
When User opens URL "https://admin-demo.nopcommerce.com/login"
And User enters Email as "<email>" and Password as "<password>"
And Click on Login
Then page title should be "Dashboard / nopCommerce administration"
When User click on Logout link
Then page title should be "Your store. Login"
And close browser

Examples:
	| email | password |
	| admin@yourstore.com | admin |
	| admin123@yourstore.com | admin123 |
