package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeTests {
	@Parameters({ "username", "password", "expectedMessage" })
	@Test(priority = 1, groups = { "negativeTests", "smoketests" })
	public void negativeLoginTest(String username, String password, String expectedErrorMessage) {
		System.out.println("Starting incorrectUserNameTest");
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		sleep(2000);

		driver.manage().window().maximize();
		sleep(2000);

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

		driver.quit();
	}

	/*
	 * @Test(priority = 2, groups = { "negativeTests" }) public void
	 * incorrectPasswordTest() {
	 * System.out.println("Starting incorrectPasswordTest");
	 * System.setProperty("webdriver.chrome.driver",
	 * "src/main/resources/chromedriver.exe");
	 * 
	 * WebDriver driver = new ChromeDriver(); sleep(2000);
	 * 
	 * driver.manage().window().maximize(); sleep(2000);
	 * 
	 * String url = "https://the-internet.herokuapp.com/login"; driver.get(url);
	 * sleep(2000);
	 * 
	 * WebElement username = driver.findElement(By.id("username"));
	 * username.sendKeys("tomsmith"); sleep(2000);
	 * 
	 * WebElement password = driver.findElement(By.name("password"));
	 * password.sendKeys("SuperSecretPassword"); sleep(5000);
	 * 
	 * WebElement loginButton = driver.findElement(By.tagName("button"));
	 * loginButton.click();
	 * 
	 * // Verifications
	 * 
	 * WebElement errorMessage = driver.findElement(By.id("flash")); String
	 * expectedErrorMessage = "Your password is invalid!"; String actualErrorMessage
	 * = errorMessage.getText();
	 * 
	 * Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
	 * "Actual error message does not contain expected error message\n");
	 * 
	 * driver.quit(); }
	 */
	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}