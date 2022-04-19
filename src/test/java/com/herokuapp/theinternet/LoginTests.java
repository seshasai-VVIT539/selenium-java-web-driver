package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests {

	private WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	private void setUp() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

		driver = new ChromeDriver();
		sleep(2000);

		driver.manage().window().maximize();
		sleep(2000);

	}

	@Test(priority = 1, groups = { "positiveTests", "smoketests" })
	public void positiveLoginTest() {
		System.out.println("Starting loginTest");

		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		sleep(2000);

		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
		sleep(2000);

		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");
		sleep(5000);

		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();

//		Verifications
		String expectedUrl = "https://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not same as the expected");

		WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logoutButton.isDisplayed(), "Logout button is not visible");

		WebElement successMessage = driver.findElement(By.cssSelector("div#flash"));
		String expectedMessage = "You logged into a secure area!";
		String actualMessage = successMessage.getText();
//		Assert.assertEquals(actualMessage, expectedMessage,"Actual message is not the same as expected");
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual message does not contain expected message\n");
	}

	@Parameters({ "username", "password", "expectedMessage" })
	@Test(priority = 2, groups = { "negativeTests", "smoketests" })
	public void negativeLoginTest(String username, String password, String expectedErrorMessage) {
		System.out.println("Starting incorrectUserNameTest");

		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		sleep(2000);

		WebElement usernameElement = driver.findElement(By.id("username"));
		usernameElement.sendKeys(username);
		sleep(2000);

		WebElement passwordelElement = driver.findElement(By.name("password"));
		passwordelElement.sendKeys(password);
		sleep(5000);

		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();

//		Verifications

		WebElement errorMessage = driver.findElement(By.id("flash"));
		String actualErrorMessage = errorMessage.getText();

		Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
				"Actual error message does not contain expected error message\n");
	}

	@AfterMethod(alwaysRun = true)
	private void tearDown() {
		driver.quit();
	}

	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
